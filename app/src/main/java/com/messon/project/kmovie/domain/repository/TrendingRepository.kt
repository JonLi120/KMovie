package com.messon.project.kmovie.domain.repository

import com.messon.project.kmovie.domain.model.Trending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {

  fun getAllTrendingList(): Flow<List<Trending>>
}