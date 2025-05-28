package com.messon.project.kmovie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messon.project.kmovie.core.UiState
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.domain.model.HomeScreenModel
import com.messon.project.kmovie.ui.home.celebrity.CelebritiesWithTitle
import com.messon.project.kmovie.ui.home.trending.TrendingItemsWithTitle
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.DevicePreviews

@Composable
fun HomeScreenRoute(
  viewModel: HomeViewModel = hiltViewModel(),
) {
  val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()
  val trendingItemsState by viewModel.trendingItems.collectAsStateWithLifecycle()
  val celebrityItems by viewModel.celebrityItems.collectAsStateWithLifecycle()

  HomeScreen(
    uiState = uiState,
    trendingItems = trendingItemsState,
    celebrityItems = celebrityItems
  )
}

@Composable
private fun HomeScreen(
  uiState: UiState<HomeScreenModel>,
  trendingItems: List<BasicTrendingModel>,
  celebrityItems: List<BasicCelebrityModel>
) {

  val isLoading = uiState is UiState.Loading

  Scaffold(
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(innerPadding)
        .windowInsetsPadding(WindowInsets.safeDrawing)
        .verticalScroll(state = rememberScrollState()),
    ) {
      TrendingItemsWithTitle(
        trendingItems = trendingItems,
      )
      Spacer(Modifier.height(60.dp))
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
      trendingItems = List(5) { BasicTrendingModel.fakeModel() },
      celebrityItems = List(5) { BasicCelebrityModel.fakeModel() }
    )
  }
}