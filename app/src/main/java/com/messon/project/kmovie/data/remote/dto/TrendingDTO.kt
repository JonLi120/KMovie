package com.messon.project.kmovie.data.remote.dto

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_BACKGROUND_PATH
import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.domain.enum.MediaType
import com.messon.project.kmovie.domain.model.BasicTrendingModel
import com.messon.project.kmovie.utils.serializer.TrendingDTOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TrendingDTOSerializer::class)
sealed class TrendingDTO {
  abstract val id: Int
  @SerialName("media_type") abstract val mediaType: String
  abstract val adult: Boolean
  abstract val popularity: Double
}

@Serializable
data class TrendingMovieDTO(
  @SerialName("id") override val id: Int,
  @SerialName("media_type") override val mediaType: String,
  @SerialName("adult") override val adult: Boolean,
  @SerialName("popularity") override val popularity: Double,
  @SerialName("backdrop_path") val backdropPath: String?,
  @SerialName("genre_ids") val genreIds: List<Int>,
  @SerialName("original_language") val originalLanguage: String,
  @SerialName("original_title") val originalTitle: String,
  @SerialName("overview") val overview: String,
  @SerialName("poster_path") val posterPath: String,
  @SerialName("release_date") val releaseDate: String,
  @SerialName("title") val title: String,
  @SerialName("video") val video: Boolean,
  @SerialName("vote_average") val voteAverage: Double = 0.0,
  @SerialName("vote_count") val voteCount: Int = 0,
): TrendingDTO()

@Serializable
data class TrendingTvDTO(
  @SerialName("id") override val id: Int,
  @SerialName("media_type") override val mediaType: String,
  @SerialName("adult") override val adult: Boolean,
  @SerialName("popularity") override val popularity: Double,
  @SerialName("backdrop_path") val backdropPath: String?,
  @SerialName("name") val name: String,
  @SerialName("original_name") val originalName: String,
  @SerialName("overview") val overview: String,
  @SerialName("poster_path") val posterPath: String,
  @SerialName("original_language") val originalLanguage: String,
  @SerialName("genre_ids") val genreIds: List<Int>,
  @SerialName("first_air_date") val firstAirDate: String,
  @SerialName("vote_average") val voteAverage: Double = 0.0,
  @SerialName("vote_count") val voteCount: Int = 0,
  @SerialName("origin_country") val originCountry: List<String>,
): TrendingDTO()

fun TrendingDTO.mapperModel(): BasicTrendingModel = when(this) {
  is TrendingMovieDTO -> BasicTrendingModel(
    id = id,
    mediaType = MediaType.parser(type = mediaType),
    title = title,
    backdropImageUrl = "${BASE_IMAGE_FOR_BACKGROUND_PATH}${backdropPath ?: posterPath}",
    posterImageUrl = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$posterPath",
    voteAverage = voteAverage,
    dateTime = releaseDate,
  )
  is TrendingTvDTO -> BasicTrendingModel(
    id = id,
    mediaType = MediaType.parser(type = mediaType),
    title = name,
    backdropImageUrl = "${BASE_IMAGE_FOR_BACKGROUND_PATH}${backdropPath ?: posterPath}",
    posterImageUrl = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$posterPath",
    voteAverage = voteAverage,
    dateTime = firstAirDate,
  )
}