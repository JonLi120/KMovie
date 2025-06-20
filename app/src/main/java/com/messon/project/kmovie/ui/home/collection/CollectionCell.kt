package com.messon.project.kmovie.ui.home.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.messon.project.kmovie.R
import com.messon.project.kmovie.domain.model.BasicMovieCollection
import com.messon.project.kmovie.ui.component.AppAsyncImage
import com.messon.project.kmovie.ui.component.SeeMoreHeader
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews
import kotlin.math.absoluteValue

@Composable
fun ColumnScope.CollectionsWithTitle(
  collections: List<BasicMovieCollection>,
) {
  val initialPage = if (collections.size > 1) 1 else 0
  val pagerState = rememberPagerState(initialPage = initialPage) {
    collections.size
  }
  SeeMoreHeader(
    headerText = stringResource(R.string.movie_collection),
    showTrailing = false,
  )
  Spacer(Modifier.height(10.dp))
  HorizontalPager(
    state = pagerState,
    modifier = Modifier.fillMaxWidth(),
    pageSpacing = (-20).dp,
    contentPadding = PaddingValues(horizontal = 48.dp)
  ) { page ->
    CollectionItem(
      pagerState = pagerState,
      page = page,
      model = collections[page]
    )
  }
}

@Composable
fun CollectionItem(
  pagerState: PagerState,
  page: Int,
  model: BasicMovieCollection
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(310.dp)
      .graphicsLayer {
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val mAlpha = lerp(0.5f, 1f, 1f - pageOffset.absoluteValue.coerceIn(0f, 1f))
        val scale = lerp(0.75f, 1f, 1f - pageOffset.absoluteValue.coerceIn(0f, 1f))
        val rotation = when {
          pageOffset < -0.5f -> 4f
          pageOffset > 0.5f -> -4f
          else -> pageOffset * -8f
        }
        rotationZ = rotation
        scaleX = scale
        scaleY = scale
        alpha = mAlpha
      },
    shape = MaterialTheme.shapes.small,
    elevation = CardDefaults.cardElevation(
      defaultElevation = 0.dp
    ),
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      AppAsyncImage(
        image = model.posterImageUrl,
        imageShape = MaterialTheme.shapes.small,
      )
      Box(
        modifier = Modifier
          .defaultMinSize(minHeight = 60.dp)
          .align(Alignment.BottomCenter),
      ) {
        Surface(
          modifier = Modifier
            .matchParentSize()
            .background(
              brush = Brush.verticalGradient(
                colors = listOf(
                  Color.White.copy(alpha = 0.1f),
                  Color.White.copy(alpha = 0.9f),
                ),
                tileMode = TileMode.Mirror
              )
            ),
          color = Color.Transparent
        ) {}
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .wrapContentSize(align = Alignment.CenterStart),
          text = model.name,
          style = MaterialTheme.typography.titleMedium
        )
      }
    }
  }
}

@Composable
@UiModePreviews
fun PreviewCollectionItem() {
  AppTheme {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
      Column {
        CollectionsWithTitle(
          collections = List(5) { BasicMovieCollection.fakeModel() }
        )
      }
    }
  }
}