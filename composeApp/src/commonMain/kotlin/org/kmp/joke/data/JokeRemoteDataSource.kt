package org.kmp.joke.data

interface JokeRemoteDataSource {
    suspend fun getJoke(): JokeDto
}