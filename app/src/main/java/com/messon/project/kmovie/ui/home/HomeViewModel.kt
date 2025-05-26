package com.messon.project.kmovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.core.asResult
import com.messon.project.kmovie.domain.model.Trending
import com.messon.project.kmovie.domain.model.mapperTrendingVO
import com.messon.project.kmovie.domain.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import okhttp3.MediaType
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  trendingRepo: TrendingRepository
) : ViewModel() {

  val trendingItemsState: StateFlow<TrendingItemsUiState> =
    trendingRepo.getAllTrendingList()
      .asResult()
      .map {
        when (it) {
          is Result.Error -> {
            Timber.e(it.exception)
            TrendingItemsUiState.Loading
          }
          is Result.Loading -> TrendingItemsUiState.Failed
          is Result.Success -> {
            val voList = it.data.map(Trending::mapperTrendingVO)
            TrendingItemsUiState.Success(voList)
          }
        }
      }
      .stateIn(
        scope = viewModelScope,
        initialValue = TrendingItemsUiState.Loading,
        started = SharingStarted.WhileSubscribed(5000)
      )
}

sealed interface TrendingItemsUiState {

  object Loading: TrendingItemsUiState
  object Failed: TrendingItemsUiState
  data class Success(val items: List<TrendingVO>): TrendingItemsUiState
}

data class TrendingVO(
  val id : Int,
  val mediaType: String,
  val title: String,
  val posterPath: String,
  val voteAverage: Double,
  val dateTime: String,
)