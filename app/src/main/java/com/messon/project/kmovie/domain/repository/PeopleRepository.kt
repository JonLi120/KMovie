package com.messon.project.kmovie.domain.repository

import com.messon.project.kmovie.core.Result
import com.messon.project.kmovie.domain.model.BasicCelebrityModel
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

  fun getPopularPersonList(page: Int = 1): Flow<Result<List<BasicCelebrityModel>>>
}