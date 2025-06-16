package com.messon.project.kmovie.domain.repository

import com.messon.project.kmovie.data.remote.dto.BasicMovieDTO
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

  fun getMovieDetail(movieId: Int): Flow<BasicMovieDTO>
}