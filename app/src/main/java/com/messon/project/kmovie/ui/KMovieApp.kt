package com.messon.project.kmovie.ui

import android.transition.Visibility
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messon.project.kmovie.ui.component.AppBottomBar

@Composable
fun KMovieApp(
  appState: KMovieAppState
) {
  var showBottomBar = appState.currentTopLevelDestination != null

  Scaffold (
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    bottomBar = {
      AnimatedVisibility(
        visible = showBottomBar,
//        enter = slideInVertically(
//          initialOffsetY = { it },
//          animationSpec = tween(durationMillis = 0)
//        ) + fadeIn(animationSpec = tween(durationMillis = 100)),
        exit = slideOutVertically(
          targetOffsetY = { it },
          animationSpec = tween(durationMillis = 100)
        ) + fadeOut(animationSpec = tween(durationMillis = 100))
      ) {
        AppBottomBar(
          destinations = appState.topLevelDestinations,
          onNavigateToDestination = appState::navigateToTopLevelDestination,
          currentDestination = appState.currentTopLevelDestination,
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier.padding(innerPadding)
        .consumeWindowInsets(innerPadding)
    ) {
      KMovieNavHost(
        appState = appState,
      )
    }
  }
}