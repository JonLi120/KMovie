package com.messon.project.kmovie.ui.trend

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.messon.project.kmovie.ui.KMovieAppState
import com.messon.project.kmovie.ui.home.HomeRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.Serializable

@Serializable
object TrendingRoute

fun NavController.navigateToTrendingScreen() {
  navigate(route = TrendingRoute)
}

@ExperimentalCoroutinesApi
fun NavGraphBuilder.trendingScreen(
  appState: KMovieAppState,
  onBackClick: () -> Unit,
) {
  val duration = 300
  composable<TrendingRoute>(
    enterTransition = {
      fadeIn(animationSpec = tween(duration)) +
        slideIntoContainer(animationSpec = tween(duration), towards = AnimatedContentTransitionScope.SlideDirection.Start)
    },
    popExitTransition = {
      fadeOut(animationSpec = tween(duration)) +
        slideOutOfContainer(animationSpec = tween(duration), towards = AnimatedContentTransitionScope.SlideDirection.End)
    }
  ) {
    TrendingScreenRoute(
      appViewModel = hiltViewModel(appState.navController.getBackStackEntry(HomeRoute)),
      onBackClick = onBackClick,
    )
  }
}