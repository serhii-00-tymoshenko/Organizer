package com.serhiitymoshenko.organizer.data.di.modules.storage

import com.serhiitymoshenko.organizer.data.di.modules.storage.children.dataStoreModule
import com.serhiitymoshenko.organizer.data.di.modules.storage.children.roomModule
import org.koin.dsl.module

val storageModule = module {

    includes(roomModule, dataStoreModule)
}