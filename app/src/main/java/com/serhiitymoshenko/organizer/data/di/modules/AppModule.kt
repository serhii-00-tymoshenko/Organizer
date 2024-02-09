package com.serhiitymoshenko.organizer.data.di.modules

import com.serhiitymoshenko.organizer.data.di.modules.networking.networkingModule
import com.serhiitymoshenko.organizer.data.di.modules.storage.storageModule
import com.serhiitymoshenko.organizer.data.di.modules.ui.uiModule
import org.koin.dsl.module

val appModule = module {

    includes(uiModule, storageModule, networkingModule)
}