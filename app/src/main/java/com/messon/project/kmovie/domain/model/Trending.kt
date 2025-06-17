package com.messon.project.kmovie.domain.model

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_BACKGROUND_PATH
import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.domain.enum.MediaType
import kotlin.random.Random

data class BasicTrendingModel(
  val id: Int,
  val mediaType: MediaType,
  val title: String,
  val backdropImageUrl: String,
  val posterImageUrl: String,
  val voteAverage: Double,
  val dateTime:  String,
) {
  companion object {
    fun fakeModel(): BasicTrendingModel = BasicTrendingModel(
      id = (1..10000).random(),
      mediaType = MediaType.MOVIE,
      title = "Jack Reacher: Never Go Back",
      backdropImageUrl = "${BASE_IMAGE_FOR_BACKGROUND_PATH}/e0kl32U3pHA2cdR1VGRk5TUmrFr.jpg",
      posterImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/j0NUh5irX7q2jIRtbLo8TZyRn6y.jpg",
      voteAverage = Random.Default.nextDouble(0.0, 10.0),
      dateTime = "2022-04-28"
    )
  }
}
