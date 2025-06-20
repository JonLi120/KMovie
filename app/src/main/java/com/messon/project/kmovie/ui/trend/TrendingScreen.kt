package com.messon.project.kmovie.ui.trend

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.messon.project.kmovie.R
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.ui.component.AppAsyncImage
import com.messon.project.kmovie.ui.component.AppStarBar
import com.messon.project.kmovie.ui.component.AppTopBar
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.theme.StadiumShape
import com.messon.project.kmovie.ui.tooling.DevicePreviews
import com.messon.project.kmovie.ui.trend.filter.MediaTypeAndTimeWindowFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
@Composable
fun TrendingScreenRoute(
  viewModel: TrendingViewModel = hiltViewModel(),
  onBackClick: () -> Unit,
) {
  val selectedMediaType by viewModel.selectedMediaType.collectAsStateWithLifecycle()
  val trendingItems = viewModel.trendingFlow.collectAsLazyPagingItems()

  TrendingScreen(
    selectedMediaType = selectedMediaType,
    trendingItems = trendingItems,
    onBackClick = onBackClick,
    onTypeSelected = viewModel::onMediaTypeChange,
    onPeriodChange = viewModel::onPeriodChange,
  )
}

@Composable
private fun TrendingScreen(
  selectedMediaType: MediaType? = MediaType.ALL,
  trendingItems: LazyPagingItems<BasicTrendingModel>,
  onBackClick: () -> Unit = {},
  onTypeSelected: (MediaType) -> Unit = {},
  onPeriodChange: (TimeWindow) -> Unit = {},
) {
  Scaffold(
    topBar = {
      AppTopBar(
        title = stringResource(R.string.trending),
        onBackClick = onBackClick
      )
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      MediaTypeAndTimeWindowFilter(
        modifier = Modifier.padding(start = 16.dp),
        selectedType = selectedMediaType,
        onTypeSelected = onTypeSelected,
        onPeriodChange = onPeriodChange
      )
      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
      ) {
        items(trendingItems.itemCount) { index ->
          val model = trendingItems[index]
          if (model != null) {
            TrendingItem(
              model =  model,
              onItemClick = {}
            )
          }
        }
      }
    }
  }
}

@Composable
private fun TrendingItem(
  modifier: Modifier = Modifier,
  model: BasicTrendingModel,
  onItemClick: () -> Unit,
) {
  Box(
    modifier = modifier
      .height(170.dp)
      .fillMaxWidth()
      .clickable { onItemClick.invoke() },
  ) {
    Box(
      modifier = Modifier
        .padding(top = 22.dp)
        .fillMaxSize()
        .clip(shape = MaterialTheme.shapes.extraSmall)
        .background(MaterialTheme.colorScheme.onBackground)
    ) {
      Column(
        modifier = Modifier
          .padding(start = 140.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
          .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
      ) {
        Box(
          modifier = Modifier
            .clip(StadiumShape)
            .background(MaterialTheme.colorScheme.onTertiary)
            .padding(horizontal = 6.dp, vertical = 4.dp)
        ) {
          Text(
            text = model.mediaType.displayName(),
            style = MaterialTheme.typography.labelSmall
          )
        }
        Text(
          text = model.title,
          color = MaterialTheme.colorScheme.background,
          style = MaterialTheme.typography.titleMedium,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = "Family / History",
          color = MaterialTheme.colorScheme.background,
          style = MaterialTheme.typography.bodySmall,
          maxLines = 1,
          overflow = TextOverflow.Clip
        )
        AppStarBar(
          modifier = Modifier.align(alignment = Alignment.End),
          rating = model.voteAverage.toFloat()
        )
      }
    }
    Box(modifier = Modifier.padding(start = 20.dp)) {
      AppAsyncImage(
        modifier = Modifier
          .size(width = 100.dp, height = 140.dp),
        image = model.posterImageUrl,
        imageShape = MaterialTheme.shapes.extraSmall,
      )
    }
  }
}

@Composable
@DevicePreviews
fun PreviewTrendingScreen() {
  val fakePagingData = PagingData.from(List(5) { BasicTrendingModel.fakeModel() })
  val pagingItems = flowOf(fakePagingData).collectAsLazyPagingItems()
  AppTheme {
    TrendingScreen(
      trendingItems = pagingItems,
    )
  }
}