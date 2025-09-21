package org.kmp.joke.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.kmp.joke.domain.GetJokesUseCase
import org.kmp.joke.domain.Result

class JokeViewModel(
    private val getJokesUseCase: GetJokesUseCase
) : ViewModel() {

    private val reload = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<JokeUiState> =
        reload
            .onStart { emit(Unit) } //initial value to trigger inner flow
            .flatMapLatest {
                getJokesUseCase()
                    .map { result ->
                        when (result) {
                            is Result.Success -> JokeUiState.Success(result.data)
                            is Result.Failure -> JokeUiState.Error(result.errorMessage)
                            is Result.Loading -> JokeUiState.Loading
                        }
                    }
                    .onStart { emit(JokeUiState.Loading) }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = JokeUiState.Loading
            )

    //button to reload jokes
    fun reloadJoke() {
        reload.tryEmit(Unit)
    }
}
