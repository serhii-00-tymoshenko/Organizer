package com.serhiitymoshenko.organizer

import android.app.Application
import android.content.Context
import com.serhiitymoshenko.organizer.data.di.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin(this)
    }

    private fun configureKoin(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(appModule)
        }
    }
}