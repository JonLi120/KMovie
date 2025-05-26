package com.messon.project.kmovie.ui.home.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.messon.project.kmovie.R
import com.messon.project.kmovie.ui.component.MovieImage
import com.messon.project.kmovie.ui.home.TrendingItemsUiState
import com.messon.project.kmovie.ui.home.TrendingVO
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.PreviewTrendingItem
import com.messon.project.kmovie.ui.tooling.UiModePreviews
import com.messon.project.kmovie.utils.DateFormatter
import com.messon.project.kmovie.utils.DateParseType

@Composable
fun TrendingTitleWithItems(trendingState: TrendingItemsUiState) {
  when(trendingState) {
    TrendingItemsUiState.Loading -> Unit
    TrendingItemsUiState.Failed -> Unit
    is TrendingItemsUiState.Success -> {
      Text(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp),
        text = stringResource(R.string.trending_title),
        style = MaterialTheme.typography.headlineLarge,
      )
      LazyRow(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        items(trendingState.items) { item ->
          TrendingListItem(item)
        }
      }
    }
  }
}

@Composable
private fun TrendingListItem(
  item: TrendingVO,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.width(150.dp),
  ) {
    Box(
      modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
    ) {
      MovieImage(
        image = item.posterPath,
        modifier = Modifier.matchParentSize()
      )
      MovieRatingBadge(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .offset(x = (-8).dp, y = 8.dp),
        voteAverage = item.voteAverage,
      )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      modifier = Modifier.padding(bottom = 4.dp, end = 6.dp),
      text = DateFormatter.extract(item.dateTime, parseType = DateParseType.Year),
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground
    )
    Text(
      modifier = Modifier.padding(end = 18.dp),
      text = item.title,
      style = MaterialTheme.typography.bodyMedium,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )
  }
}

@Composable
private fun MovieRatingBadge(
  modifier: Modifier = Modifier,
  voteAverage: Double,
) {
  Box(
    modifier = modifier
      .wrapContentSize()
      .clip(shape = MaterialTheme.shapes.small)
      .background(Color.DarkGray.copy(alpha = 0.7f))
      .padding(horizontal = 6.dp, vertical = 4.dp),
    contentAlignment = Alignment.Center,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = Color.Yellow,
        modifier = Modifier.size(12.dp),
      )
      Text(
        text = "%.1f".format(voteAverage),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onPrimary,
      )
    }
  }
}

@Composable
@UiModePreviews
fun MovieRatingBadgePreview() {
  AppTheme {
    MovieRatingBadge(
      voteAverage = 5.0
    )
  }
}

@Composable
@UiModePreviews
fun TrendingListItemPreview() {
  AppTheme {
    TrendingListItem(PreviewTrendingItem)
  }
}