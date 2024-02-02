package com.serhiitymoshenko.organizer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serhiitymoshenko.organizer.data.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureKoin(this)
    }

    private fun configureKoin(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(remoteModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}