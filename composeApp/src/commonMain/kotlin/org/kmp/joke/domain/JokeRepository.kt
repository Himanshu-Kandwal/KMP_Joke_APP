package org.kmp.joke.domain

import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun getJoke(): Flow<Result<Joke>>
}