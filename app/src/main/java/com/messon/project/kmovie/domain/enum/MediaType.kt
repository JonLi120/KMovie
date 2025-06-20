package com.messon.project.kmovie.domain.enum

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.messon.project.kmovie.R

enum class MediaType {
  UNKNOWN,
  ALL,
  MOVIE,
  TV,
  PERSON;

  companion object {
    fun parser(type: String): MediaType = when(type) {
      "movie" -> MOVIE
      "tv" -> TV
      "person" -> PERSON
      else -> UNKNOWN
    }
  }

  fun lowercaseName(): String = this.name.lowercase()

  @Composable
  fun displayName(): String = when(this) {
    UNKNOWN -> ""
    ALL -> stringResource(R.string.all)
    MOVIE -> stringResource(R.string.movie)
    TV -> stringResource(R.string.tv)
    PERSON -> ""
  }
}