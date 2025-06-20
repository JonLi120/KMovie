package com.messon.project.kmovie.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.messon.project.kmovie.core.Constants
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadTrendingDataUseCase @Inject constructor(
  private val trendingRepository: TrendingRepository,
) {
  operator fun invoke(mediaType: MediaType, timeWindow: TimeWindow): Flow<PagingData<BasicTrendingModel>> {
    return Pager(
      config = PagingConfig(pageSize = Constants.PAGE_MAX_SIZE),
      pagingSourceFactory = {
        trendingRepository.loadTrending(mediaType, timeWindow)
      }
    ).flow
  }
}