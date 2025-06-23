package com.messon.project.kmovie.data

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.data.local.entity.GenreEntity
import com.messon.project.kmovie.data.remote.dto.BasicPersonDTO
import com.messon.project.kmovie.data.remote.dto.GenreDTO
import com.messon.project.kmovie.data.remote.dto.MovieCollection
import com.messon.project.kmovie.domain.enum.GenderType
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import com.messon.project.kmovie.domain.model.BasicMovieCollection
import com.messon.project.kmovie.domain.model.GenreModel

fun MovieCollection.mapperToModel(): BasicMovieCollection = BasicMovieCollection(
  id = id,
  name = name,
  posterImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/${posterPath ?: backdropPath}",
  backdropImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/${backdropPath ?: posterPath}",
)

fun BasicPersonDTO.mapperToModel(): BasicCelebrityModel = BasicCelebrityModel(
  id = id,
  adult = adult,
  gender = GenderType.parser(gender),
  name = name,
  originalName = originalName,
  profileImage = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$profilePath",
)

fun GenreDTO.mapperToEntity(): GenreEntity = GenreEntity(
  id = id,
  name = name,
)