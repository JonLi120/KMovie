package com.messon.project.kmovie.data.repository

import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.BasicPersonDTO
import com.messon.project.kmovie.data.remote.dto.mapperModel
import javax.inject.Inject
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.repository.PeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class PeopleRepositoryImpl @Inject constructor(
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val dataSource: AppNetworkDataSource,
): BaseRepository(ioDispatcher), PeopleRepository {

  override fun getPopularPersonList(page: Int): Flow<Result<List<BasicCelebrityModel>>> = execute {
    dataSource.getPopularPersonList(page = page)
      .results
      .map(BasicPersonDTO::mapperModel)
  }
}