package com.messon.project.kmovie.di

import com.messon.project.kmovie.BuildConfig
import com.messon.project.kmovie.data.remote.AppNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule {

  private const val CONNECT_TIMEOUT: Long = 5
  private const val WRITE_TIMEOUT: Long = 30
  private const val READ_TIMEOUT: Long = 30
  private const val BASE_URL: String = "https://api.themoviedb.org"

  @Provides
  @Singleton
  fun provideHeaderInterceptor() : Interceptor = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
      val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}")
        .build()
      return chain.proceed(request)
    }
  }

  @Provides
  @Singleton
  fun provideOkHttpFactory(headerInterceptor : Interceptor): Call.Factory = OkHttpClient.Builder()
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor(headerInterceptor)
    .addInterceptor(
      HttpLoggingInterceptor { message ->
        Timber.tag("Http").d(message = message)
      }.apply {
        if (BuildConfig.DEBUG) {
          setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
          setLevel(HttpLoggingInterceptor.Level.NONE)
        }
      }
    ).build()

  @Provides
  @Singleton
  fun provideRetrofit(callFactory: dagger.Lazy<Call.Factory>): Retrofit {
    val mediaType = "application/json; charset=UTF8".toMediaType()
    val networkJson = Json {
      ignoreUnknownKeys = true
      encodeDefaults = true
      coerceInputValues = true
      explicitNulls = false
    }
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .callFactory {
        callFactory.get().newCall(it)
      }
      .addConverterFactory(networkJson.asConverterFactory(mediaType))
      .build()
  }

  @Provides
  @Singleton
  fun provideAppService(retrofit: Retrofit): AppNetworkDataSource {
    return retrofit.create(AppNetworkDataSource::class.java)
  }
}
