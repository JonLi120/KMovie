package com.messon.project.kmovie.di

import com.messon.project.kmovie.data.AppRepository
import com.messon.project.kmovie.data.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

  @Singleton
  @Binds
  abstract fun bindsAppRepository(
    appRepository: AppRepositoryImpl
  ): AppRepository
}
