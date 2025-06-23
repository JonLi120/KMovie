package com.messon.project.kmovie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messon.project.kmovie.domain.repository.GenreRepository
import com.messon.project.kmovie.domain.usecase.LoadAndSaveLocalGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
  private val loadAndSaveLocalGenreUseCase: LoadAndSaveLocalGenreUseCase,
  private val genreRepository: GenreRepository,
): ViewModel() {

  val hasGenres = genreRepository.checkGenresIsEmpty()
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(),
      initialValue = true,
    )

  init {
    Timber.i("@@@ appViewModel init:: ${this.hashCode()}")

    viewModelScope.launch {
      hasGenres.collect {
        Timber.d("@@@ genreIsEmpty change:: $it")
        if (!it) {
          loadAndSaveLocalGenreUseCase.invoke()
        }
      }
    }
  }
}