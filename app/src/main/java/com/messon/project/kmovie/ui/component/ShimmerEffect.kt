package com.messon.project.kmovie.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ShimmerEffect(
  modifier: Modifier,
  background: Color = Color.LightGray,
  brushColor: Color = Color.White,
  brushWidth: Int = 700,
  angle: Float = 270f,
  duration: Int = 1000
) {
  val shimmerColors = listOf(
    brushColor.copy(alpha = 0.3f),
    brushColor.copy(alpha = 0.5f),
    brushColor.copy(alpha = 1f),
    brushColor.copy(alpha = 0.5f),
    brushColor.copy(alpha = 0.3f),
  )
  
  val transition = rememberInfiniteTransition()
  val translateAnimation = transition.animateFloat(
    initialValue = 0f,
    targetValue = (duration + brushWidth).toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = duration,
        easing = LinearEasing,
      ),
      repeatMode = RepeatMode.Restart,
    ),
    label = "",
  )
  val brush = Brush.linearGradient(
    colors = shimmerColors,
    start = Offset(x = translateAnimation.value - brushWidth, y = 0.0f),
    end = Offset(x = translateAnimation.value, y = angle),
  )
  Box(
    modifier = modifier.background(background)
  ) {
    Spacer(
      modifier = Modifier
        .matchParentSize()
        .background(brush)
    )
  }
}