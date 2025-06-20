package com.messon.project.kmovie.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
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

  fun <T: Any> executeWithPaging(call: suspend (Int) -> List<T>) = object : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
      return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
      val page = params.key ?: 1
      val pageSize = params.loadSize
      val result = call.invoke(page)
      return runCatching {
        LoadResult.Page(
          data = result,
          prevKey = if (page == 1) null else page - 1,
          nextKey = if (result.isEmpty()) null else page + 1,
        )
      }.getOrElse(onFailure = { t ->
        LoadResult.Error(t)
      })
    }
  }
}