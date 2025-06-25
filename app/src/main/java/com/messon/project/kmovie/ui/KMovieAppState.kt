package com.messon.project.kmovie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.messon.project.kmovie.ui.component.TopLevelDestination
import com.messon.project.kmovie.ui.home.navigateToHomeScreen
import com.messon.project.kmovie.ui.movie.navigateToMovieScreen
import com.messon.project.kmovie.ui.tv.navigateToTvScreen

@Composable
fun rememberKMovieAppState(
  navController: NavHostController = rememberNavController(),
) = remember(navController) {
  KMovieAppState(navController)
}

@Stable
class KMovieAppState(val navController: NavHostController) {

  val currentDestination: NavDestination?
    @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination

  val currentTopLevelDestination: TopLevelDestination?
    @Composable
    get() {
      return TopLevelDestination.entries.firstOrNull { destination ->
        currentDestination?.hasRoute(route = destination.route) == true
      }
    }

  val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

  fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    val navOption = navOptions {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
    when(topLevelDestination) {
      TopLevelDestination.HOME -> navController.navigateToHomeScreen(navOption)
      TopLevelDestination.MOVIE -> navController.navigateToMovieScreen(navOption)
      TopLevelDestination.TV -> navController.navigateToTvScreen(navOption)
    }
  }
}