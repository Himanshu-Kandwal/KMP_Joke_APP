package org.kmp.joke.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.kmp.joke.domain.Joke
import org.kmp.joke.domain.JokeRepository
import org.kmp.joke.domain.Result

class JokeRepositoryImpl(
    val jokeRemoteDataSource: JokeRemoteDataSource,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokeRepository {
    override fun getJoke(): Flow<Result<Joke>> {
        return flow {
            try {
                emit(Result.Loading)
                val joke = jokeRemoteDataSource.getJoke().toJoke()
                emit(Result.Success(joke))
            } catch (e: Exception) {
                emit(Result.Failure(e.message ?: "Unknown error"))
            }
        }.flowOn(dispatcher)
    }
}