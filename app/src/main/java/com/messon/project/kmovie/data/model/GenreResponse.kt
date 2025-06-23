package com.messon.project.kmovie.data.model

import com.messon.project.kmovie.data.remote.dto.GenreDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
  @SerialName("genres") val genres: List<GenreDTO>
)