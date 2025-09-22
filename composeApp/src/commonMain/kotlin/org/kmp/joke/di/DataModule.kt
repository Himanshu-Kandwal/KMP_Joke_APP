package org.kmp.joke.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.kmp.joke.Constants
import org.kmp.joke.data.JokeRemoteDataSource
import org.kmp.joke.data.JokeRemoteDataSourceImpl
import org.kmp.joke.data.JokeRepositoryImpl
import org.kmp.joke.domain.JokeRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single(named("ioDispatcher")) { Dispatchers.IO }

    single<JokeRepository> {
        JokeRepositoryImpl(get(), get(named("ioDispatcher")))
    }

    single<JokeRemoteDataSource> {
        JokeRemoteDataSourceImpl(
            httpClient = get(), baseUrl = Constants.URL
        )
    }
}