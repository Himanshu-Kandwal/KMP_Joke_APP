package org.kmp.joke.di

import org.kmp.joke.ui.JokeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiModule = module {
    singleOf(::JokeViewModel)
}