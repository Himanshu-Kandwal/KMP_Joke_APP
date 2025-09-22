package org.kmp.joke

import android.app.Application
import org.kmp.joke.di.androidDataModule
import org.kmp.joke.di.dataModule
import org.kmp.joke.di.domainModule
import org.kmp.joke.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokeApplication)
            modules(domainModule, dataModule, androidDataModule, uiModule)
        }
    }
}