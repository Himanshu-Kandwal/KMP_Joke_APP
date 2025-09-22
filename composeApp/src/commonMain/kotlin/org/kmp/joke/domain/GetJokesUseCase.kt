package org.kmp.joke.domain

import kotlinx.coroutines.flow.Flow

class GetJokesUseCase(val jokeRepository: JokeRepository) {
    operator fun invoke(): Flow<Result<Joke>> = jokeRepository.getJoke()
}