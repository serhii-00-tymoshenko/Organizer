package com.serhiitymoshenko.organizer

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serhiitymoshenko.organizer.data.di.coroutinesModule
import com.serhiitymoshenko.organizer.data.di.databaseModule
import com.serhiitymoshenko.organizer.data.di.firebaseModule
import com.serhiitymoshenko.organizer.data.di.viewModelsModule
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
            modules(coroutinesModule, databaseModule, firebaseModule, viewModelsModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}