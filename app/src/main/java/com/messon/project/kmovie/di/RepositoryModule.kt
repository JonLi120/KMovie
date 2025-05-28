package com.messon.project.kmovie.di

import com.messon.project.kmovie.data.repository.PeopleRepositoryImpl
import com.messon.project.kmovie.data.repository.TrendingRepositoryImpl
import com.messon.project.kmovie.domain.repository.PeopleRepository
import com.messon.project.kmovie.domain.repository.TrendingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

  @Binds
  fun bindTrendingRepository(
    impl: TrendingRepositoryImpl
  ): TrendingRepository

  @Binds
  fun bindPeopleRepository(
    impl: PeopleRepositoryImpl
  ): PeopleRepository
}