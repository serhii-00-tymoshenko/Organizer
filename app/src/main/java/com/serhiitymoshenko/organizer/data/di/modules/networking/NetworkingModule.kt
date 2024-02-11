package com.serhiitymoshenko.organizer.data.di.modules.networking

import org.koin.dsl.module

val networkingModule = module {

    includes(firebaseModule)
}