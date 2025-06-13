package com.messon.project.kmovie.data.remote.dto

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.domain.enum.GenderType
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicPersonDTO(
  val id : Int,
  val adult: Boolean,
  val gender: Int,
  val name: String,
  @SerialName("original_name") val originalName: String,
  @SerialName("profile_path") val profilePath: String = "",
)

fun BasicPersonDTO.mapperModel(): BasicCelebrityModel = BasicCelebrityModel(
  id = id,
  adult = adult,
  gender = GenderType.parser(gender),
  name = name,
  originalName = originalName,
  profileImage = "$BASE_IMAGE_FOR_THUMBNAIL_PATH$profilePath",
)