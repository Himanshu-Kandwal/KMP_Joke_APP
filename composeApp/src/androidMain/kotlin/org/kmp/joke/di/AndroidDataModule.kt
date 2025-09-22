package org.kmp.joke.di

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kmp.joke.BuildConfig
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
            install(Logging) {
                level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
                logger = Logger.DEFAULT
            }
        }
    }
}