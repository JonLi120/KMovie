package com.messon.project.kmovie.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository(
  private val dispatcher: CoroutineDispatcher
) {

  fun <T> executeWithResult(call: suspend () -> T): Flow<Result<T>> = flow {
    emit(call.invoke())
  }
    .asResult()
    .flowOn(dispatcher)

  fun <T> execute(call: suspend () -> T): Flow<T> = flow {
    emit(call.invoke())
  }.flowOn(dispatcher)
}