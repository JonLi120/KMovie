package com.messon.project.kmovie.di

import com.messon.project.kmovie.BuildConfig
import com.messon.project.kmovie.data.remote.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule {

  private const val CONNECT_TIMEOUT: Long = 10
  private const val WRITE_TIMEOUT: Long = 30
  private const val READ_TIMEOUT: Long = 30
  private const val BASE_URL: String = ""

  @Provides
  @Singleton
  fun provideOkHttpFactory(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor(
      HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
          setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
          setLevel(HttpLoggingInterceptor.Level.NONE)
        }
      }
    ).build()

  @Provides
  @Singleton
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    val mediaType = "application/json; charset=UTF8".toMediaType()
    val networkJson = Json {
      ignoreUnknownKeys = true
      encodeDefaults = true
      explicitNulls = false
    }
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(client)
      .addConverterFactory(networkJson.asConverterFactory(mediaType))
      .build()
  }

  @Provides
  fun provideAppService(retrofit: Retrofit): AppService {
    return retrofit.create(AppService::class.java)
  }
}
