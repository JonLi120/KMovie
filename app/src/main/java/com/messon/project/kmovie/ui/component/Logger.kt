package com.messon.project.kmovie.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.messon.project.kmovie.BuildConfig
import timber.log.Timber

@Composable
fun ComposableLogger(tag: String? = null, msg: String, isDebug: Boolean = BuildConfig.DEBUG) {
  if (isDebug) {
    var recompositionCount by remember { mutableIntStateOf(0) }
    recompositionCount++

    Timber.tag(tag ?: "").d("$recompositionCount, $msg")
  }
}