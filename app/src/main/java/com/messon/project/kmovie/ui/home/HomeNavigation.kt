package com.messon.project.kmovie.ui.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.messon.project.kmovie.ui.KMovieAppState
import kotlinx.serialization.Serializable

@Serializable
object HomeBaseRoute
@Serializable
object HomeRoute

fun NavController.navigateToHomeScreen(navOptions: NavOptions?) {
  navigate(route = HomeRoute, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(
  appState: KMovieAppState,
  onTrendingCellClick: (() -> Unit),
  trendingDestination: NavGraphBuilder.() -> Unit
) {
  navigation<HomeBaseRoute>(
    startDestination = HomeRoute
  ) {
    composable<HomeRoute> {
      HomeScreenRoute(
        appViewModel = hiltViewModel(appState.navController.getBackStackEntry(HomeRoute)),
        onTrendingCellClick = onTrendingCellClick,
      )
    }
    trendingDestination()
  }
}