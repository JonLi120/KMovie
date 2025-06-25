package com.messon.project.kmovie.ui.movie

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.messon.project.kmovie.ui.KMovieAppState
import kotlinx.serialization.Serializable

@Serializable
object MovieRoute

fun NavController.navigateToMovieScreen(navOptions: NavOptions?) {
  navigate(route = MovieRoute, navOptions = navOptions)
}

fun NavGraphBuilder.movieScreen(
  appState: KMovieAppState
) {
  composable<MovieRoute> {
    MovieScreenRoute()
  }
}