package com.messon.project.kmovie.data.remote.dto

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.domain.model.BasicMovieCollection
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BasicMovieDTO(
  val adult: Boolean,
  @SerialName("backdrop_path") val backdropPath: String?,
  @SerialName("belongs_to_collection") val belongsToCollection: MovieCollection?,
  val budget: Int,
  val genres: List<Genre>,
  val homepage: String,
  val id: Int,
  @SerialName("imdb_id") val imdbId: String,
  @SerialName("origin_country") val originCountry: List<String>,
  @SerialName("original_language") val originalLanguage: String,
  @SerialName("original_title") val originalTitle: String,
  val overview: String,
  val popularity: Double,
  @SerialName("poster_path") val posterPath: String,
  @SerialName("release_date") val releaseDate: String,
  val revenue: Int,
  val runtime: Int,
  @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
  val status: String,
  val tagline: String,
  val title: String,
  val video: Boolean,
  @SerialName("vote_average") val voteAverage: Double,
  @SerialName("vote_count") val voteCount: Int
)

@Serializable
data class Genre(
  val id: Int,
  val name: String
)

@Serializable
data class SpokenLanguage(
  @SerialName("english_name") val englishName: String,
  val name: String
)

@Serializable
data class MovieCollection(
  val id: Int,
  val name: String,
  @SerialName("backdrop_path") val backdropPath: String?,
  @SerialName("poster_path") val posterPath: String?
)

fun MovieCollection.mapperModel(): BasicMovieCollection = BasicMovieCollection(
  id = id,
  name = name,
  posterImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/${posterPath ?: backdropPath}",
  backdropImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/${backdropPath ?: posterPath}",
)