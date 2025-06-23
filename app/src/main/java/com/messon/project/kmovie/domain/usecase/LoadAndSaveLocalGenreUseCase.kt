package com.messon.project.kmovie.domain.usecase

import com.messon.project.kmovie.data.local.dao.GenreDao
import com.messon.project.kmovie.data.mapperToEntity
import com.messon.project.kmovie.data.remote.dto.GenreDTO
import com.messon.project.kmovie.domain.repository.GenreRepository
import javax.inject.Inject

class LoadAndSaveLocalGenreUseCase @Inject constructor(
  private val genreRepository: GenreRepository,
  private val genreDao: GenreDao,
) {
  suspend operator fun invoke() {
    val genresEntity = genreRepository.getGenresFromApi()
      .map(GenreDTO::mapperToEntity)

    genreDao.insertGenres(genresEntity)
  }
}