package com.messon.project.kmovie.domain.repository

import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {

  fun getAllTrendingList(): Flow<Result<List<BasicTrendingModel>>>
}