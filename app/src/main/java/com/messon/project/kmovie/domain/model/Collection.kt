package com.messon.project.kmovie.domain.model

import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH

data class BasicMovieCollection(
  val id: Int,
  val name: String,
  val posterImageUrl: String,
  val backdropImageUrl: String,
) {
  companion object {
    fun fakeModel(): BasicMovieCollection = BasicMovieCollection(
      id = (1..10000).random(),
      name = "Star Wars Collection",
      posterImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/aSrMJYmQX8kpF26LijkCsYhBMvm.jpg",
      backdropImageUrl = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/qVPChlozQ1BP3svfHjiAdNneMGA.jpg"
    )
  }
}