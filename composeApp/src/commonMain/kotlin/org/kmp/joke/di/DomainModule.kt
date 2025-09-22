package org.kmp.joke.di

import org.kmp.joke.domain.GetJokesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetJokesUseCase)
}