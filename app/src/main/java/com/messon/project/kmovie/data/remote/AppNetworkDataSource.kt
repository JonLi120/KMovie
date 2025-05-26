package com.messon.project.kmovie.data.remote

import com.messon.project.kmovie.data.model.ApiResponse
import com.messon.project.kmovie.data.remote.dto.TrendingDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AppNetworkDataSource {

  @GET("3/trending/all/day")
  suspend fun getAllTrendingList(@Query("language") language: String = "en-US"): ApiResponse<TrendingDTO>

}