package com.messon.project.kmovie.ui.trend.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.messon.project.kmovie.R
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.enum.TimeWindow
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews

@Composable
fun MediaTypeAndTimeWindowFilter(
  modifier: Modifier = Modifier,
  selectedType: MediaType? = MediaType.ALL,
  onTypeSelected: (MediaType) -> Unit,
  onPeriodChange: (TimeWindow) -> Unit,
) {
  var popupMenuDisplay by remember { mutableStateOf(false) }
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    FilterChip(
      selected = selectedType == MediaType.ALL,
      onClick = { onTypeSelected(MediaType.ALL) },
      label = { Text(stringResource(R.string.all)) },
      modifier = Modifier.weight(1f)
    )
    FilterChip(
      selected = selectedType == MediaType.MOVIE,
      onClick = { onTypeSelected(MediaType.MOVIE) },
      label = { Text(stringResource(R.string.movie)) },
      modifier = Modifier.weight(1f)
    )
    FilterChip(
      selected = selectedType == MediaType.TV,
      onClick = { onTypeSelected(MediaType.TV) },
      label = { Text(stringResource(R.string.tv)) },
      modifier = Modifier.weight(1f)
    )
    Box {
      IconButton(
        onClick = {
          popupMenuDisplay = true
        }
      ) {
        Icon(
          imageVector = Icons.Rounded.DateRange,
          contentDescription = null,
        )
        DropdownMenu(
          expanded = popupMenuDisplay,
          onDismissRequest = {
            popupMenuDisplay = false
          }
        ) {
          DropdownMenuItem(
            text = { Text(stringResource(R.string.day)) },
            onClick = {
              onPeriodChange(TimeWindow.DAY)
              popupMenuDisplay = false
            }
          )
          DropdownMenuItem(
            text = { Text(stringResource(R.string.week)) },
            onClick = {
              onPeriodChange(TimeWindow.WEEK)
              popupMenuDisplay = false
            }
          )
        }
      }
    }
  }
}

@Composable
@UiModePreviews
private fun PreviewMediaTypeAndTimeWindowFilter() {
  AppTheme {
    MediaTypeAndTimeWindowFilter(
      modifier = Modifier.width(512.dp),
      onTypeSelected = {},
      onPeriodChange = {}
    )
  }
}