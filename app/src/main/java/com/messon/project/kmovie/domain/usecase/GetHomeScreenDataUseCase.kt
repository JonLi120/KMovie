package com.messon.project.kmovie.domain.usecase

import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.core.UiState
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.HomeScreenModel
import com.messon.project.kmovie.domain.repository.PeopleRepository
import com.messon.project.kmovie.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeScreenDataUseCase @Inject constructor(
  private val peopleRepository: PeopleRepository,
  private val trendingRepository: TrendingRepository,
) {
  operator fun invoke(): Flow<UiState<HomeScreenModel>> = combine(
    peopleRepository.getPopularPersonList(),
    trendingRepository.getAllTrendingList(),
  ) { celebrityResult, trendingResult ->
    if (celebrityResult is Result.Loading || trendingResult is Result.Loading) {
      return@combine UiState.Loading
    }
    val trendingItems: List<BasicTrendingModel> = trendingResult.let {
      if (it is Result.Success) {
        it.data
      } else {
        listOf()
      }
    }
    val celebrityItems: List<BasicCelebrityModel> = celebrityResult.let {
      if (it is Result.Success) {
        it.data
      } else {
        listOf()
      }
    }

    UiState.Success(
      HomeScreenModel(
        trendingItems = trendingItems,
        celebrities = celebrityItems,
      )
    )
  }
}