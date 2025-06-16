package com.messon.project.kmovie.domain.model

data class HomeScreenModel(
  val collections: List<BasicMovieCollection>,
  val trendingItems: List<BasicTrendingModel>,
  val celebrities: List<BasicCelebrityModel>
)