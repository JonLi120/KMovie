package com.messon.project.kmovie.data.repository

import androidx.paging.PagingSource
import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import com.messon.project.kmovie.data.remote.dto.TrendingMovieDTO
import com.messon.project.kmovie.data.remote.dto.TrendingTvDTO
import com.messon.project.kmovie.data.remote.dto.mapperModel
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.GenreModel
import com.messon.project.kmovie.domain.repository.TrendingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val dataSource: AppNetworkDataSource,
): BaseRepository(ioDispatcher), TrendingRepository {

  override fun getAllTrendingList(): Flow<Result<List<BasicTrendingModel>>> = executeWithResult {
    dataSource.getAllTrendingList()
      .results
      .filterNot { it.mediaType == MediaType.PERSON.lowercaseName() }
      .map(TrendingDTO::mapperModel)
  }

  override fun loadTrending(mediaType: MediaType, timeWindow: TimeWindow, genres: List<GenreModel>): PagingSource<Int, BasicTrendingModel> = executeWithPaging { page: Int ->
    val genreMap = genres.associate { it.id to it.name }
    dataSource.getTrending(
      mediaType = mediaType.lowercaseName(),
      timeWindow = timeWindow.lowercaseName(),
      page= page,
    ).results
      .filterNot { it.mediaType == MediaType.PERSON.lowercaseName() }
      .map { dto ->
        when(dto) {
          is TrendingTvDTO -> {
            dto.mapperModel().copy(genres = dto.genreIds.mapNotNull { genreMap[it] }.joinToString(" / "))
          }
          is TrendingMovieDTO -> {
            dto.mapperModel().copy(genres = dto.genreIds.mapNotNull { genreMap[it] }.joinToString(" / "))
          }
        }
      }
  }
}