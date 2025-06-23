package com.messon.project.kmovie.domain.model

import com.messon.project.kmovie.data.local.entity.GenreEntity

data class GenreModel(
  val id: Int,
  val name: String,
) {
  companion object {
    fun fakeModel(): GenreModel = GenreModel(
      id = 0,
      name = "Test"
    )
  }
}

fun GenreModel.mapperToEntity(): GenreEntity  = GenreEntity(
  id = id,
  name = name,
)