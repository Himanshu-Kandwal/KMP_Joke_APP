package org.kmp.joke.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.kmp.joke.domain.GetJokesUseCase
import org.kmp.joke.domain.Joke
import org.kmp.joke.domain.Result

class JokeViewModel(getJokesUseCase: GetJokesUseCase) : ViewModel() {

    val uiState: StateFlow<JokeUiState> =
        getJokesUseCase()
            .map { result: Result<Joke> ->
                when (result) {
                    is Result.Success -> JokeUiState.Success(result.data)
                    is Result.Failure -> JokeUiState.Error(result.errorMessage)
                    is Result.Loading -> JokeUiState.Loading
                }
            }
            .onStart { emit(JokeUiState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = JokeUiState.Loading
            )
}