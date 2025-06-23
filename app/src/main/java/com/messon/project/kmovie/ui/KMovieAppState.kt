package com.messon.project.kmovie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.messon.project.kmovie.ui.home.HomeRoute

@Composable
fun rememberKMovieAppState(
  navController: NavHostController = rememberNavController(),
) = remember(navController) {
  KMovieAppState(navController)
}

@Stable
class KMovieAppState(val navController: NavHostController) {

  fun navigateBack() {
    navController.popBackStack()
  }
}