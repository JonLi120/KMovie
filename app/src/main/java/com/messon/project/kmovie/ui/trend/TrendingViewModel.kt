package com.messon.project.kmovie.ui.trend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.usecase.LoadTrendingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TrendingViewModel @Inject constructor(
  private val loadTrendingDataUseCase: LoadTrendingDataUseCase,
): ViewModel() {

  private val _selectedMediaType: MutableStateFlow<MediaType> = MutableStateFlow(MediaType.ALL)
  val selectedMediaType: StateFlow<MediaType> = _selectedMediaType.asStateFlow()

  private val _selectedTimeWindow: MutableStateFlow<TimeWindow> = MutableStateFlow(TimeWindow.DAY)

  val trendingFlow = combine(_selectedMediaType, _selectedTimeWindow) { type, time ->
    Pair(type, time)
  }.flatMapLatest { pair ->
    loadTrendingDataUseCase(_selectedMediaType.value, _selectedTimeWindow.value)
  }.cachedIn(viewModelScope)

  fun onMediaTypeChange(type: MediaType) {
    _selectedMediaType.value = type
  }

  fun onPeriodChange(timeWindow: TimeWindow) {
    _selectedTimeWindow.value = timeWindow
  }
}