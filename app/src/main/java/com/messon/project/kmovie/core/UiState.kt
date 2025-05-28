package com.messon.project.kmovie.core

sealed class UiState<out T> {

  data class Success<T>(val data: T) : UiState<T>()

  data class Error(val exception: Throwable? = null) : UiState<Nothing>()

  data object Loading : UiState<Nothing>()
}