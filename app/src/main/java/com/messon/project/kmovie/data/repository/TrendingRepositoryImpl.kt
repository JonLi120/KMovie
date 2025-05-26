package com.messon.project.kmovie.data.repository

import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import com.messon.project.kmovie.data.remote.dto.mapperModel
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.model.Trending
import com.messon.project.kmovie.domain.repository.TrendingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val dataSource: AppNetworkDataSource,
): TrendingRepository {

  override fun getAllTrendingList(): Flow<List<Trending>> = flow {
    emit(
      dataSource.getAllTrendingList()
        .results
        .filterNot { it.mediaType == "person" }
        .map(TrendingDTO::mapperModel)
    )
  }.flowOn(ioDispatcher)
}