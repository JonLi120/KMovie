package com.messon.project.kmovie.domain.model

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.ui.home.TrendingVO

sealed interface Trending

data class TrendingMovie(
  val id: Int,
  val mediaType: String,
  val adult: Boolean,
  val popularity: Double,
  val backdropPath: String,
  val genreIds: List<Int>,
  val originalLanguage: String,
  val originalTitle: String,
  val overview: String,
  val posterPath: String,
  val releaseDate: String,
  val title: String,
  val video: Boolean,
  val voteAverage: Double = 0.0,
  val voteCount: Int = 0,
): Trending

data class TrendingTv(
  val id: Int,
  val mediaType: String,
  val adult: Boolean,
  val popularity: Double,
  val backdropPath: String,
  val name: String,
  val originalName: String,
  val overview: String,
  val posterPath: String,
  val originalLanguage: String,
  val genreIds: List<Int>,
  val firstAirDate: String,
  val voteAverage: Double = 0.0,
  val voteCount: Int = 0,
  val originCountry: List<String>,
): Trending

data class TrendingPerson(
  val id: Int,
  val mediaType: String,
  val adult: Boolean,
  val popularity: Double,
  val name: String,
  val originalName: String,
  val gender: Int,
  val knownForDepartment: String,
  val profilePath: String,
): Trending

fun Trending.mapperTrendingVO(): TrendingVO = when(this) {
  is TrendingMovie -> {
    TrendingVO(
      id = id,
      mediaType = mediaType,
      title = title,
      posterPath = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$posterPath",
      voteAverage = voteAverage,
      dateTime = releaseDate,
    )
  }
  is TrendingTv -> {
    TrendingVO(
      id = id,
      mediaType = mediaType,
      title = name,
      posterPath = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$posterPath",
      voteAverage = voteAverage,
      dateTime = firstAirDate
    )
  }
  is TrendingPerson -> error("No implementation of trending person ui")
}
