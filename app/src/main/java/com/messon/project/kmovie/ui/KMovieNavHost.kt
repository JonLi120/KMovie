package com.messon.project.kmovie.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.messon.project.kmovie.ui.home.HomeBaseRoute
import com.messon.project.kmovie.ui.home.homeScreen
import com.messon.project.kmovie.ui.movie.movieScreen
import com.messon.project.kmovie.ui.trend.navigateToTrendingScreen
import com.messon.project.kmovie.ui.trend.trendingScreen
import com.messon.project.kmovie.ui.tv.tvScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun KMovieNavHost(
  appState: KMovieAppState,
) {
  val navController = appState.navController
  NavHost(
    navController = navController,
    startDestination = HomeBaseRoute,
  ) {
    homeScreen(
      appState = appState,
      onTrendingCellClick = navController::navigateToTrendingScreen
    ) {
      trendingScreen(
        appState = appState,
        onBackClick = navController::popBackStack
      )
    }
    movieScreen(
      appState = appState,
    )
    tvScreen(
      appState = appState,
    )
  }
}