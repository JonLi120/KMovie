package com.messon.project.kmovie.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import com.messon.project.kmovie.data.remote.dto.mapperModel
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.model.BasicTrendingModel
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

  override fun loadTrending(mediaType: MediaType, timeWindow: TimeWindow): PagingSource<Int, BasicTrendingModel> = executeWithPaging { page: Int ->
    dataSource.getTrending(
      mediaType = mediaType.lowercaseName(),
      timeWindow = timeWindow.lowercaseName(),
      page= page,
    ).results
      .filterNot { it.mediaType == MediaType.PERSON.lowercaseName() }
      .map(TrendingDTO::mapperModel)
  }

//  fun loadTrending(mediaType: MediaType, timeWindow: TimeWindow) : PagingSource<Int, BasicTrendingModel> {
//    return object : PagingSource<Int, BasicTrendingModel>() {
//      override fun getRefreshKey(state: PagingState<Int, BasicTrendingModel>): Int? {
//        return null
//      }
//
//      override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BasicTrendingModel> {
//        return runCatching {
//          LoadResult.Page(
//            data = dataSource.getAllTrendingList().results.filterNot { it.mediaType == "person" }.map(TrendingDTO::mapperModel),
//            prevKey = 1,
//            nextKey = 2,
//          )
//        }.getOrElse(onFailure = { t ->
//          LoadResult.Error(t)
//        })
//      }
//    }
//  }
}