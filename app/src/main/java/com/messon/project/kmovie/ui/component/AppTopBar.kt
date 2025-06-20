package com.messon.project.kmovie.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.messon.project.kmovie.R
import com.messon.project.kmovie.ui.theme.AppTheme
import com.messon.project.kmovie.ui.tooling.UiModePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  modifier: Modifier = Modifier,
  title: String,
  showBackIcon: Boolean = true,
  onBackClick: (() -> Unit)? = null,
) {
  CenterAlignedTopAppBar(
    modifier = modifier,
    title = {
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
      )
    },
    navigationIcon = {
      if (showBackIcon) {
        IconButton(
          enabled = onBackClick != null,
          onClick = onBackClick ?: {}
        ) {
          Icon(
            painter = painterResource(R.drawable.ic_arrow_back_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  )
}

@Composable
@UiModePreviews
fun PreviewAppTopBar() {
  AppTheme {
    AppTopBar(
      title = stringResource(android.R.string.untitled),
    )
  }
}