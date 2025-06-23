package com.messon.project.kmovie.data.remote.dto

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