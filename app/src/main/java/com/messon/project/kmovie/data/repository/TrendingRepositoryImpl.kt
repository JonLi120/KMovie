package com.messon.project.kmovie.data.repository

import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import com.messon.project.kmovie.data.remote.dto.mapperModel
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
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
      .filterNot { it.mediaType == "person" }
      .map(TrendingDTO::mapperModel)
  }
}