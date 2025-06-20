package com.messon.project.kmovie.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeScreen(
  onTrendingCellClick: (() -> Unit)
) {
  composable<HomeRoute> {
    HomeScreenRoute(
      onTrendingCellClick = onTrendingCellClick,
    )
  }
}