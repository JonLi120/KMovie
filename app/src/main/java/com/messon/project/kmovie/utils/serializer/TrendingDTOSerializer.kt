package com.messon.project.kmovie.utils.serializer

import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import com.messon.project.kmovie.data.remote.dto.TrendingMovieDTO
import com.messon.project.kmovie.data.remote.dto.TrendingPersonDTO
import com.messon.project.kmovie.data.remote.dto.TrendingTvDTO
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class TrendingDTOSerializer : KSerializer<TrendingDTO> {
  override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
    serialName = "TrendingDTO"
  )

  override fun serialize(encoder: Encoder, value: TrendingDTO) {
    val jsonEncoder = encoder as? JsonEncoder ?: error("TrendingDTOSerializer can only be used with Json")

    val jsonElement = when(value) {
      is TrendingMovieDTO -> jsonEncoder.json.encodeToJsonElement(TrendingMovieDTO.serializer(), value)
      is TrendingPersonDTO -> jsonEncoder.json.encodeToJsonElement(TrendingPersonDTO.serializer(), value)
      is TrendingTvDTO -> jsonEncoder.json.encodeToJsonElement(TrendingTvDTO.serializer(), value)
    }

    jsonEncoder.encodeJsonElement(jsonElement)
  }

  override fun deserialize(decoder: Decoder): TrendingDTO {
    val input = decoder as? JsonDecoder ?: error("TrendingDTOSerializer can only be used with Json")
    val jsonElement = input.decodeJsonElement()
    val jsonObject = jsonElement.jsonObject

    return when(val mediaType = jsonObject["media_type"]?.jsonPrimitive?.contentOrNull) {
      "movie" -> input.json.decodeFromJsonElement(TrendingMovieDTO.serializer(), jsonObject)
      "tv" -> input.json.decodeFromJsonElement(TrendingTvDTO.serializer(), jsonObject)
      "person" -> input.json.decodeFromJsonElement(TrendingPersonDTO.serializer(), jsonObject)
      else -> error("Unknown media_type: $mediaType")
    }
  }

}