package com.messon.project.kmovie.data

import com.messon.project.kmovie.data.remote.AppService
import javax.inject.Inject

interface AppRepository {

}

class AppRepositoryImpl @Inject constructor(
  private val service: AppService
) : AppRepository {

}