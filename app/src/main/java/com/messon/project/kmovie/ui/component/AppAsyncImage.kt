package com.messon.project.kmovie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.messon.project.kmovie.R
import com.messon.project.kmovie.ui.tooling.UiModePreviews

@Composable
fun AppAsyncImage(
  modifier: Modifier = Modifier,
  image: String,
  contentDescription: String? = null,
  errorPainter: Painter = painterResource(R.drawable.ic_image_not_supported_24),
  imageShape: Shape = MaterialTheme.shapes.medium,
) {
  var imageSize by remember { mutableStateOf(IntSize.Zero) }

  val halfSize = with(LocalDensity.current) {
    (imageSize.width / 1.2).toInt().toDp()
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .onSizeChanged { imageSize = it }
      .clip(shape = imageShape)
  ) {
    SubcomposeAsyncImage(
      model = image,
      modifier = Modifier.fillMaxSize(),
      contentDescription = contentDescription,
      contentScale = ContentScale.Crop,
      loading = {
        ShimmerEffect(
          modifier = modifier
        )
      },
      error = {
        ErrorImage(
          modifier = modifier,
          size = halfSize,
          painter = errorPainter,
        )
      },
      success = {
        SubcomposeAsyncImageContent()
      },
    )
  }
}

@Composable
private fun ErrorImage(
  modifier: Modifier = Modifier,
  size: Dp,
  painter: Painter,
  contentAlignment: Alignment = Alignment.Center
) {
  Box(
    modifier = modifier.background(color = Color.LightGray),
    contentAlignment = contentAlignment,
  ) {
    Image(
      modifier = Modifier.size(size = size),
      painter = painter,
      contentDescription = null,
      contentScale = ContentScale.Fit,
      colorFilter = ColorFilter.tint(color = Color(0x7AFFFFFF))
    )
  }
}

@Composable
@UiModePreviews
private fun PreviewRoundCornersImage() {
  AppAsyncImage(
    modifier = Modifier.size(height = 200.dp, width = 150.dp),
    image = ""
  )
}

@Composable
@UiModePreviews
private fun PreviewCircleImage() {
  AppAsyncImage(
    modifier = Modifier.size(height = 150.dp, width = 150.dp),
    image = "",
    imageShape = CircleShape,
    errorPainter = painterResource(R.drawable.ic_person_24),
  )
}