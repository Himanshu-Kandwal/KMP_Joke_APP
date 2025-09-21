package org.kmp.joke.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetJokesUseCase(val jokeRepository: JokeRepository) {
    operator fun invoke(): Flow<Result<Joke>> = flow { jokeRepository.getJoke() }
}