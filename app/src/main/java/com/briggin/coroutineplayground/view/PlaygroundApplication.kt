package com.briggin.coroutineplayground.view

import android.app.Application
import com.briggin.coroutineplayground.koinModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        startTimber()
    }

    private fun startTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@PlaygroundApplication)
            modules(koinModule)
        }
    }
}
