package com.serhiitymoshenko.organizer.data.di.modules.networking

import com.serhiitymoshenko.organizer.data.di.modules.networking.childmodules.firebaseModule
import org.koin.dsl.module

val networkingModule = module {

    includes(firebaseModule)
}