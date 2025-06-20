package com.messon.project.kmovie.domain.repository

import androidx.paging.PagingSource
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {

  fun getAllTrendingList(): Flow<Result<List<BasicTrendingModel>>>

  fun loadTrending(mediaType: MediaType, timeWindow: TimeWindow): PagingSource<Int, BasicTrendingModel>
}