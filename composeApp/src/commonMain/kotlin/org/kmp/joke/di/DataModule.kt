package org.kmp.joke.di

import org.kmp.joke.data.JokeRemoteDataSourceImpl
import org.kmp.joke.data.JokeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::JokeRepository)
    singleOf(::JokeRemoteDataSourceImpl)
}