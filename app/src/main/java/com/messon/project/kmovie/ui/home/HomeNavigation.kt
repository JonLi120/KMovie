package com.messon.project.kmovie.ui.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.messon.project.kmovie.ui.KMovieAppState
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeScreen(
  appState: KMovieAppState,
  onTrendingCellClick: (() -> Unit)
) {
  composable<HomeRoute> {
    HomeScreenRoute(
      appViewModel = hiltViewModel(appState.navController.getBackStackEntry(HomeRoute)),
      onTrendingCellClick = onTrendingCellClick,
    )
  }
}