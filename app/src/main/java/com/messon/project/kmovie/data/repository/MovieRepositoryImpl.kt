package com.messon.project.kmovie.data.repository

import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.BasicMovieDTO
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val dataSource: AppNetworkDataSource,
) : BaseRepository(ioDispatcher), MovieRepository {

  override fun getMovieDetail(movieId: Int): Flow<BasicMovieDTO> = execute {
    dataSource.getMovieDetail(movieId = movieId)
  }
}