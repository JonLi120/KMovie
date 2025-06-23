package com.messon.project.kmovie.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messon.project.kmovie.core.UiState
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicMovieCollection
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.HomeScreenModel
import com.messon.project.kmovie.ui.AppViewModel
import com.messon.project.kmovie.ui.home.celebrity.CelebritiesWithTitle
import com.messon.project.kmovie.ui.home.collection.CollectionsWithTitle
import com.messon.project.kmovie.ui.home.trending.TrendingItemsWithTitle
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.DevicePreviews

@Composable
fun HomeScreenRoute(
  appViewModel: AppViewModel = hiltViewModel(),
  viewModel: HomeViewModel = hiltViewModel(),
  onTrendingCellClick: (() -> Unit)?,
) {
  val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
  val collectionItems by viewModel.collectionItems.collectAsStateWithLifecycle()
  val trendingItemsState by viewModel.trendingItems.collectAsStateWithLifecycle()
  val celebrityItems by viewModel.celebrityItems.collectAsStateWithLifecycle()

  HomeScreen(
    uiState = uiState,
    collectionItems = collectionItems,
    trendingItems = trendingItemsState,
    celebrityItems = celebrityItems,
    onTrendingCellClick = onTrendingCellClick,
  )
}

@Composable
private fun HomeScreen(
  uiState: UiState<HomeScreenModel>,
  collectionItems: List<BasicMovieCollection>,
  trendingItems: List<BasicTrendingModel>,
  celebrityItems: List<BasicCelebrityModel>,
  onTrendingCellClick: (() -> Unit)? = null,
) {

  val isLoading = uiState is UiState.Loading

  Box(
    modifier = Modifier.background(MaterialTheme.colorScheme.background)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .systemBarsPadding()
        .windowInsetsPadding(WindowInsets.safeDrawing)
        .verticalScroll(state = rememberScrollState()),
    ) {

      if (collectionItems.isNotEmpty()) {
        CollectionsWithTitle(
          collections = collectionItems,
        )
        Spacer(Modifier.height(20.dp))
      }
      TrendingItemsWithTitle(
        trendingItems = trendingItems,
        onMoreClick = onTrendingCellClick,
      )
      Spacer(Modifier.height(40.dp))
      CelebritiesWithTitle(
        celebrityItems = celebrityItems,
      )
    }
  }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen(
      uiState = UiState.Loading,
      collectionItems = List(5) { BasicMovieCollection.fakeModel() },
      trendingItems = List(5) { BasicTrendingModel.fakeModel() },
      celebrityItems = List(5) { BasicCelebrityModel.fakeModel() }
    )
  }
}