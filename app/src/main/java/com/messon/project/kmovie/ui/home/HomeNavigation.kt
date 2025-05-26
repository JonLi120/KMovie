package com.messon.project.kmovie.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object Home

fun NavGraphBuilder.homeScreen() {
  composable<Home> {
    HomeScreenRoute()
  }
}