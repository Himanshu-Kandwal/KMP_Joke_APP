package org.kmp.joke.ui

import org.kmp.joke.domain.Joke

sealed interface JokeUiState {
    data class Success(val joke: String) : JokeUiState
    object Loading : JokeUiState
    data class Error(val message: String) : JokeUiState
}
