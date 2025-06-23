package com.messon.project.kmovie.domain.repository

import com.messon.project.kmovie.data.remote.dto.GenreDTO
import com.messon.project.kmovie.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow


interface GenreRepository {
  fun getLocalGenres(): Flow<List<GenreModel>>

  fun checkGenresIsEmpty(): Flow<Boolean>

  suspend fun getGenresFromApi(): List<GenreDTO>
}