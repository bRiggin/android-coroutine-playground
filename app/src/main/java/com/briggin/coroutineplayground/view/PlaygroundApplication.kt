package com.briggin.coroutineplayground.view

import android.app.Application
import com.briggin.coroutineplayground.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlaygroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@PlaygroundApplication)
            modules(koinModule)
        }
    }
}
