package com.serhiitymoshenko.organizer.data.di.modules.storage

import com.serhiitymoshenko.organizer.data.di.modules.storage.childmodules.dataStoreModule
import com.serhiitymoshenko.organizer.data.di.modules.storage.childmodules.roomModule
import org.koin.dsl.module

val storageModule = module {

    includes(roomModule, dataStoreModule)
}