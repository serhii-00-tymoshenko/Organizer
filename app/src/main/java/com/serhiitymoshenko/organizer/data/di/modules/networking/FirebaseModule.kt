package com.serhiitymoshenko.organizer.data.di.modules.networking

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {

    single<FirebaseAuth> {
        Firebase.auth
    }

    single<FirebaseFirestore> {
        Firebase.firestore
    }
}