package com.messon.project.kmovie.domain.enum

enum class MediaType {
  UNKNOWN,
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
}