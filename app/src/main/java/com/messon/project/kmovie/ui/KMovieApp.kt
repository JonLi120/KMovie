package com.messon.project.kmovie.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.messon.project.kmovie.ui.home.HomeRoute
import com.messon.project.kmovie.ui.home.homeScreen
import com.messon.project.kmovie.ui.trend.navigateToTrendingScreen
import com.messon.project.kmovie.ui.trend.trendingScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun KMovieApp(
  appState: KMovieAppState = rememberKMovieAppState()
) {
  val navController = appState.navController
  NavHost(
    navController = navController,
    startDestination = HomeRoute,
  ) {
    homeScreen(
      onTrendingCellClick = navController::navigateToTrendingScreen
    )
    trendingScreen(
      onBackClick = navController::popBackStack
    )
  }
}