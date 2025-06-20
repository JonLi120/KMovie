package com.messon.project.kmovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messon.project.kmovie.core.UiState
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicMovieCollection
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.HomeScreenModel
import com.messon.project.kmovie.domain.usecase.GetHomeScreenDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val getHomeScreenDataUseCase: GetHomeScreenDataUseCase,
) : ViewModel() {

  var homeUiState = MutableStateFlow<UiState<HomeScreenModel>>(UiState.Loading)
    private set

  var trendingItems = MutableStateFlow<List<BasicTrendingModel>>(emptyList())
    private set

  var celebrityItems = MutableStateFlow<List<BasicCelebrityModel>>(emptyList())
    private set

  var collectionItems = MutableStateFlow<List<BasicMovieCollection>>(emptyList())
    private set

  init {
    getTrendingAndCelebrityItems()
  }

  private fun getTrendingAndCelebrityItems() {
//    collectionItems.value = List(5) { BasicMovieCollection.fakeModel() }
//    trendingItems.value = List(5) { BasicTrendingModel.fakeModel() }
//    celebrityItems.value = List(5) { BasicCelebrityModel.fakeModel() }
//    return
    getHomeScreenDataUseCase.invoke()
      .onEach { state ->
        homeUiState.value = state
        if (state is UiState.Success) {
          val model: HomeScreenModel = state.data
          collectionItems.value = model.collections
          trendingItems.value = model.trendingItems
          celebrityItems.value = model.celebrities
        }
      }.launchIn(viewModelScope)
  }
}