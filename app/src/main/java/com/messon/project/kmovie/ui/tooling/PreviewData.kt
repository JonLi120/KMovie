package com.messon.project.kmovie.ui.tooling


import com.messon.project.kmovie.core.Constants.BASE_IMAGE_FOR_THUMBNAIL_PATH
import com.messon.project.kmovie.ui.home.TrendingVO
import kotlin.random.Random

fun fakeTrendingVO(): TrendingVO = TrendingVO(
  id = (1..10000).random(),
  mediaType = "movie",
  title = "Jack Reacher: Never Go Back",
  posterPath = "${BASE_IMAGE_FOR_THUMBNAIL_PATH}/j0NUh5irX7q2jIRtbLo8TZyRn6y.jpg",
  voteAverage = Random.Default.nextDouble(0.0, 10.0),
  dateTime = "2022-04-28"
)

val PreviewTrendingItem: TrendingVO
  get() = fakeTrendingVO()

val PreviewTrendingItems: List<TrendingVO>
  get() = List(5) { fakeTrendingVO() }
