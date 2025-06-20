package com.messon.project.kmovie.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.messon.project.kmovie.R
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews

@Composable
fun AppStarBar(
  modifier: Modifier = Modifier,
  rating: Float,
  starSize: Dp = 16.dp,
  horizontalSpace: Dp = 0.dp,
  tint: Color = Color.Yellow,
  emptyTint: Color = MaterialTheme.colorScheme.background
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
  ) {
    val outlineStar = painterResource(id = R.drawable.ic_round_star_outline_24)
    val fullStar = painterResource(id = R.drawable.ic_round_star_24)
    val starCount = 5
    val clampedRating: Float = rating.coerceIn(0f, 10f) / 2

    for(index in 0 until starCount) {
      val starRating = clampedRating - index
      val fillFraction = starRating.coerceIn(0f, 1f)

      Box(
        modifier = Modifier
          .size(starSize)
          .drawWithCache {
            onDrawWithContent {
              drawContent()
              drawWithLayer(size = size) {
                with(fullStar) {
                  draw(
                    size = size,
                    colorFilter = ColorFilter.tint(emptyTint)
                  )
                }
                val width = size.width * fillFraction
                drawRect(
                  color = tint,
                  size = Size(width, size.height),
                  blendMode = BlendMode.SrcAtop
                )
              }
            }
          }
      ) {
        Icon(
          modifier = Modifier
            .matchParentSize()
            .align(Alignment.Center),
          painter = outlineStar,
          contentDescription = null,
          tint = emptyTint
        )
//        Canvas(modifier = Modifier.fillMaxSize()) {
//          with(outlineStar) {
//            draw(
//              size = size,
//              colorFilter = ColorFilter.tint(Color.Gray, blendMode = BlendMode.SrcIn),
//            )
//          }
//        }
      }
    }
  }
}

private fun DrawScope.drawWithLayer(
  size: Size,
  content: DrawScope.() -> Unit,
) {
  drawIntoCanvas { canvas ->
    canvas.saveLayer(bounds = Rect(Offset.Zero, Offset(size.width, size.height)), paint = Paint())
    content()
    canvas.restore()
  }
}

@Composable
@UiModePreviews
fun PreviewAppStarBar() {
  AppTheme {
    AppStarBar(
      modifier = Modifier.height(24.dp),
      rating = 5.0f,
      starSize = 24.dp,
      tint = Color.Yellow,
      emptyTint = Color.Gray
    )
  }
}