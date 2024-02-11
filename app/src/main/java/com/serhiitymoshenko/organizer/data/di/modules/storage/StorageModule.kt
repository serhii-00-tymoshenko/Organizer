package com.serhiitymoshenko.organizer.data.di.modules.storage

import org.koin.dsl.module

val storageModule = module {

    includes(roomModule, dataStoreModule)
}