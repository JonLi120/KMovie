package com.messon.project.kmovie.ui.trend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.repository.GenreRepository
import com.messon.project.kmovie.domain.usecase.LoadTrendingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TrendingViewModel @Inject constructor(
  private val loadTrendingDataUseCase: LoadTrendingDataUseCase,
  private val genreRepository: GenreRepository
): ViewModel() {

  private val _selectedMediaType: MutableStateFlow<MediaType> = MutableStateFlow(MediaType.ALL)
  val selectedMediaType: StateFlow<MediaType> = _selectedMediaType.asStateFlow()

  private val _selectedTimeWindow: MutableStateFlow<TimeWindow> = MutableStateFlow(TimeWindow.DAY)

  private val _genres = genreRepository.getLocalGenres().stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(),
    initialValue = emptyList()
  )

  val trendingFlow = combine(_selectedMediaType, _selectedTimeWindow, _genres) { type, time, genres ->
    Triple(type, time, genres)
  }.flatMapLatest { pair ->
    loadTrendingDataUseCase(pair.first, pair.second, pair.third)
  }.cachedIn(viewModelScope)

  fun onMediaTypeChange(type: MediaType) {
    _selectedMediaType.value = type
  }

  fun onPeriodChange(timeWindow: TimeWindow) {
    _selectedTimeWindow.value = timeWindow
  }
}