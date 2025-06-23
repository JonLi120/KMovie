package com.messon.project.kmovie.data.repository

import com.messon.project.kmovie.core.BaseRepository
import com.messon.project.kmovie.data.local.dao.GenreDao
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import com.messon.project.kmovie.data.remote.dto.GenreDTO
import com.messon.project.kmovie.di.AppDispatchers.IO
import com.messon.project.kmovie.di.Dispatcher
import com.messon.project.kmovie.domain.model.GenreModel
import com.messon.project.kmovie.domain.repository.GenreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
  private val dataSource: AppNetworkDataSource,
  private val genreDao: GenreDao,
): BaseRepository(ioDispatcher), GenreRepository {

  override fun getLocalGenres(): Flow<List<GenreModel>> = execute {
    genreDao.getAllGenres()
  }

  override fun checkGenresIsEmpty(): Flow<Boolean> = genreDao.hasGenreData()

  override suspend fun getGenresFromApi(): List<GenreDTO> = withContext(ioDispatcher) {
      listOf(
        async { dataSource.getMovieGenres() },
        async { dataSource.getTvGenres() },
      ).awaitAll()
        .flatMap { it.genres }
  }
}