package com.messon.project.kmovie.ui.tv

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.messon.project.kmovie.ui.KMovieAppState
import kotlinx.serialization.Serializable

@Serializable
object TvRoute

fun NavController.navigateToTvScreen(navOptions: NavOptions?) {
  navigate(route = TvRoute, navOptions = navOptions)
}

fun NavGraphBuilder.tvScreen(
  appState: KMovieAppState,
) {
  composable<TvRoute> {
    TvScreenRoute()
  }
}