package com.serhiitymoshenko.organizer.data.di.modules.ui

import com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules.contactsModule
import com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules.coroutinesModule
import com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules.todoModule
import org.koin.dsl.module

val uiModule = module {

    includes(contactsModule, coroutinesModule, todoModule)
}