package org.kmp.joke.di

import org.kmp.joke.data.JokeRemoteDataSourceImpl
import org.kmp.joke.data.JokeRepositoryImpl
import org.kmp.joke.domain.JokeRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::JokeRepositoryImpl) {
        bind<JokeRepository>()
    }
    singleOf(::JokeRemoteDataSourceImpl)
}