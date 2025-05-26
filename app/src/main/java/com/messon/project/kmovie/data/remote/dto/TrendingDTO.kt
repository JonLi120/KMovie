package com.messon.project.kmovie.data.remote.dto

import com.messon.project.kmovie.domain.model.Trending
import com.messon.project.kmovie.domain.model.TrendingMovie
import com.messon.project.kmovie.domain.model.TrendingPerson
import com.messon.project.kmovie.domain.model.TrendingTv
import com.messon.project.kmovie.utils.serializer.TrendingDTOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TrendingDTOSerializer::class)
sealed class TrendingDTO {
  abstract val id: Int
  abstract val mediaType: String
  abstract val adult: Boolean
  abstract val popularity: Double
}

@Serializable
data class TrendingMovieDTO(
  @SerialName("id") override val id: Int,
  @SerialName("media_type") override val mediaType: String,
  @SerialName("adult") override val adult: Boolean,
  @SerialName("popularity") override val popularity: Double,
  @SerialName("backdrop_path") val backdropPath: String,
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
  @SerialName("backdrop_path") val backdropPath: String,
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

@Serializable
data class TrendingPersonDTO(
  @SerialName("id") override val id: Int,
  @SerialName("media_type") override val mediaType: String,
  @SerialName("adult") override val adult: Boolean,
  @SerialName("popularity") override val popularity: Double,
  @SerialName("name") val name: String,
  @SerialName("original_name") val originalName: String,
  @SerialName("gender") val gender: Int,
  @SerialName("known_for_department") val knownForDepartment: String,
  @SerialName("profile_path") val profilePath: String,
): TrendingDTO()

fun TrendingDTO.mapperModel(): Trending = when(this) {
  is TrendingMovieDTO -> TrendingMovie(
    id = id,
    mediaType = mediaType,
    adult = adult,
    popularity = popularity,
    backdropPath = backdropPath,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
  )

  is TrendingPersonDTO -> TrendingPerson(
    id = id,
    mediaType = mediaType,
    adult = adult,
    popularity = popularity,
    name = name,
    originalName = originalName,
    gender = gender,
    knownForDepartment = knownForDepartment,
    profilePath = profilePath
  )

  is TrendingTvDTO -> TrendingTv(
    id = id,
    mediaType = mediaType,
    adult = adult,
    popularity = popularity,
    backdropPath = backdropPath,
    name = name,
    originalName = originalName,
    overview = overview,
    posterPath = posterPath,
    originalLanguage = originalLanguage,
    genreIds = genreIds,
    firstAirDate = firstAirDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    originCountry = originCountry
  )
}