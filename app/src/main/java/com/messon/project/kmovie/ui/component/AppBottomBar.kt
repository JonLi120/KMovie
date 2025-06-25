package com.messon.project.kmovie.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.messon.project.kmovie.R
import com.messon.project.kmovie.ui.home.HomeRoute
import com.messon.project.kmovie.ui.movie.MovieRoute
import com.messon.project.kmovie.ui.tv.TvRoute
import timber.log.Timber
import kotlin.reflect.KClass

enum class TopLevelDestination(
  @DrawableRes val selectedIcon: Int,
  @DrawableRes val unselectedIcon: Int,
  @StringRes val titleTextId: Int,
  val route: KClass<*>
) {
  HOME(
    selectedIcon = R.drawable.ic_home_24,
    unselectedIcon = R.drawable.ic_outline_home_24,
    titleTextId = R.string.home,
    route = HomeRoute::class
  ),
  MOVIE(
    selectedIcon = R.drawable.ic_movies_24,
    unselectedIcon = R.drawable.ic_outline_movies_24,
    titleTextId = R.string.movie,
    route = MovieRoute::class
  ),
  TV(
    selectedIcon = R.drawable.ic_tv_24,
    unselectedIcon = R.drawable.ic_outline_tv_24,
    titleTextId = R.string.tv,
    route = TvRoute::class
  )
}

@Composable
fun AppBottomBar(
  destinations: List<TopLevelDestination>,
  onNavigateToDestination: (TopLevelDestination) -> Unit,
  currentDestination: TopLevelDestination?,
  modifier: Modifier = Modifier,
) {
  NavigationBar(
    modifier = modifier,
  ) {
    destinations.forEach { destination ->
      val selected = currentDestination == destination
      NavigationBarItem(
        selected = selected,
        onClick = { onNavigateToDestination(destination) },
        icon = {
          Icon(
            painter = if (selected) painterResource(destination.selectedIcon) else painterResource(destination.unselectedIcon),
            contentDescription = null
          )
        },
        label = {
          Text(stringResource(destination.titleTextId))
        }
      )
    }
  }
}