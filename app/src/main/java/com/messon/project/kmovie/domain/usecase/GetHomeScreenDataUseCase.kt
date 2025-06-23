package com.messon.project.kmovie.domain.usecase

import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.core.UiState
import com.messon.project.kmovie.data.mapperToModel
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.HomeScreenModel
import com.messon.project.kmovie.domain.repository.MovieRepository
import com.messon.project.kmovie.domain.repository.PeopleRepository
import com.messon.project.kmovie.domain.repository.TrendingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetHomeScreenDataUseCase @Inject constructor(
  private val peopleRepository: PeopleRepository,
  private val trendingRepository: TrendingRepository,
  private val movieRepository: MovieRepository,
) {
  operator fun invoke(): Flow<UiState<HomeScreenModel>> = combine(
    peopleRepository.getPopularPersonList(),
    trendingRepository.getAllTrendingList(),
  ) { celebrityResult, trendingResult ->
    val result: UiState<HomeScreenModel> = if (celebrityResult is Result.Loading || trendingResult is Result.Loading) {
      UiState.Loading
    } else {
      val trendingItems: List<BasicTrendingModel> = trendingResult.let {
        when(it) {
          is Result.Success -> it.data
          is Result.Error -> {
            Timber.e(it.exception)
            emptyList()
          }
          Result.Loading -> emptyList()
        }
      }
      val celebrityItems: List<BasicCelebrityModel> = celebrityResult.let {
        when(it) {
          is Result.Success -> it.data
          is Result.Error -> {
            Timber.e(it.exception)
            emptyList()
          }
          Result.Loading -> emptyList()
        }
      }
      UiState.Success(
        HomeScreenModel(
          collections = emptyList(),
          trendingItems = trendingItems,
          celebrities = celebrityItems
        )
      )
    }
    result
  }.flatMapLatest { result ->
    flow {
      if (result !is UiState.Success) {
        emit(result)
        return@flow
      }
      val model = result.data
      val trendingItems = model.trendingItems

      val movieCollections = coroutineScope {
        trendingItems
          .filter { it.mediaType == MediaType.MOVIE }
          .map {
            async {
              runCatching {
                movieRepository.getMovieDetail(it.id).first()
              }.onFailure { t -> Timber.e(t) }
                .getOrNull()
            }
          }
          .awaitAll()
          .mapNotNull { dto -> dto?.belongsToCollection?.mapperToModel() }
      }

      emit(
        UiState.Success(
          data = model.copy(
            collections = movieCollections,
          )
        )
      )
    }
  }
}