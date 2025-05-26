package com.messon.project.kmovie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messon.project.kmovie.ui.home.trending.TrendingTitleWithItems
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.DevicePreviews
import com.messon.project.kmovie.ui.tooling.PreviewTrendingItems

@Composable
fun HomeScreenRoute(
  viewModel: HomeViewModel = hiltViewModel(),
) {
  val trendingItemsState by viewModel.trendingItemsState.collectAsStateWithLifecycle()
  HomeScreen(
    trendingUiState = trendingItemsState,
  )
}

@Composable
private fun HomeScreen(trendingUiState: TrendingItemsUiState) {

  val isTrendingLoading = trendingUiState is TrendingItemsUiState.Loading

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
      if (!isTrendingLoading) {
        TrendingTitleWithItems(
          trendingState = trendingUiState,
        )
      }
    }
  }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen() {
  AppTheme {
    HomeScreen(TrendingItemsUiState.Success(items = PreviewTrendingItems))
  }
}