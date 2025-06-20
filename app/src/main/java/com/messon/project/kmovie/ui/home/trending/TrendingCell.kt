package com.messon.project.kmovie.ui.home.trending

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.messon.project.kmovie.R
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.ui.component.AppAsyncImage
import com.messon.project.kmovie.ui.component.SeeMoreHeader
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews
import com.messon.project.kmovie.utils.DateFormatter
import com.messon.project.kmovie.utils.DateParseType

@Composable
fun ColumnScope.TrendingItemsWithTitle(
  trendingItems: List<BasicTrendingModel>,
  onMoreClick:(() -> Unit)? = null,
) {
  SeeMoreHeader(
    headerText = stringResource(R.string.trending_now),
    onMoreClick = onMoreClick,
  )
  Box {
    if (trendingItems.isNotEmpty()) {
      val backdropImage: String = trendingItems.random().backdropImageUrl
      AsyncImage(
        modifier = Modifier.matchParentSize(),
        model = backdropImage,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = .9f,
      )
      Box (
        modifier = Modifier
          .matchParentSize()
          .background(Color.White.copy(alpha = .4f))
      ){}
    }
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        .background(Color.Transparent),
      horizontalArrangement = Arrangement.spacedBy(20.dp),
      contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
      items(trendingItems) { item ->
        TrendingListItem(item)
      }
    }
  }
}

@Composable
private fun TrendingListItem(
  item: BasicTrendingModel,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.width(200.dp),
  ) {
    Box(
      modifier = Modifier
        .height(260.dp)
        .fillMaxWidth()
    ) {
      AppAsyncImage(
        image = item.posterImageUrl,
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
    val titleStyle = MaterialTheme.typography.bodyMedium
    Box(
      modifier = Modifier
        .heightIn(min = (titleStyle.lineHeight.value * 2).dp)
    ) {
      Text(
        modifier = Modifier.padding(end = 18.dp),
        text = item.title,
        style = titleStyle,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
    }
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
fun TrendingTitleWithItemsPreview() {
  AppTheme {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
      Column {
        TrendingItemsWithTitle(
          trendingItems = List(2) {
            BasicTrendingModel.fakeModel()
          }
        )
      }
    }
  }
}