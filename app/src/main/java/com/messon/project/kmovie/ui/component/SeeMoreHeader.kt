package com.messon.project.kmovie.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.messon.project.kmovie.R

@Composable
fun SeeMoreHeader(
  headerText: String,
  showTrailing: Boolean = true,
  onMoreClick: (() -> Unit)? = null,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp, horizontal = 16.dp)
      .clickable(
        enabled = showTrailing,
      ) {
        onMoreClick?.invoke()
      },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = headerText,
      style = MaterialTheme.typography.headlineLarge,
    )
    if (showTrailing) {
      Image(
        modifier = Modifier.size(28.dp),
        painter = painterResource(R.drawable.ic_rounded_chevron_right_24),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground,)
      )
    }
  }
}