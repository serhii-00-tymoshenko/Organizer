package com.serhiitymoshenko.organizer.ui

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.serhiitymoshenko.organizer.R
import com.serhiitymoshenko.organizer.data.di.coroutinesModule
import com.serhiitymoshenko.organizer.data.di.localModule
import com.serhiitymoshenko.organizer.data.di.firebaseModule
import com.serhiitymoshenko.organizer.data.di.viewModelsModule
import com.serhiitymoshenko.organizer.databinding.ActivityMainBinding
import com.serhiitymoshenko.organizer.ui.auth.AuthFragment
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureKoin(this)
    }

    private fun configureKoin(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(coroutinesModule, localModule, firebaseModule, viewModelsModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}