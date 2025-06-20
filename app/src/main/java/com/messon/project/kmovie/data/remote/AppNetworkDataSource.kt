package com.messon.project.kmovie.data.remote

import com.messon.project.kmovie.data.model.ApiResponse
import com.messon.project.kmovie.data.remote.dto.BasicMovieDTO
import com.messon.project.kmovie.data.remote.dto.BasicPersonDTO
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
const val API_VERSION: String = "3"

interface AppNetworkDataSource {

  // === Trending ===
  @GET("$API_VERSION/trending/{media_type}/{time_window}")
  suspend fun getTrending(
    @Path("media_type") mediaType: String,
    @Path("time_window") timeWindow: String,
    @Query("language") language: String = "en-US",
    @Query("page") page: Int
  ): ApiResponse<TrendingDTO>

  @GET("$API_VERSION/trending/all/day")
  suspend fun getAllTrendingList(@Query("language") language: String = "en-US"): ApiResponse<TrendingDTO>

  // === Person ===
  @GET("$API_VERSION/person/popular")
  suspend fun getPopularPersonList(
    @Query("language") language: String = "en-US",
    @Query("page") page: Int = 1
  ): ApiResponse<BasicPersonDTO>

  // === Movie ===
  @GET("$API_VERSION/movie/{movie_id}")
  suspend fun getMovieDetail(
    @Path("movie_id") movieId: Int,
    @Query("language") language: String = "en-US",
  ): BasicMovieDTO
}