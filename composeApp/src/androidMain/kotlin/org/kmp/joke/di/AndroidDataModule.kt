package org.kmp.joke.di

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kmp.joke.data.createPlatformHttpClient
import org.koin.dsl.module

val androidDataModule = module {
    single {
        createPlatformHttpClient().config { //platform specific client is used here to configure
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })

            }
        }
    }
}