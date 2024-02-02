package com.serhiitymoshenko.organizer.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val remoteModule = module {
    single<FirebaseAuth> { Firebase.auth }
}