package com.messon.project.kmovie.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.messon.project.kmovie.ui.home.Home
import com.messon.project.kmovie.ui.home.homeScreen

@Composable
fun KMovieApp(
  appState: KMovieAppState = rememberKMovieAppState()
) {
  NavHost(
    navController = appState.navController,
    startDestination = Home,
  ) {
    homeScreen()
  }
}