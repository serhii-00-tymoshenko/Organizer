package com.serhiitymoshenko.organizer.data.di.modules.ui

import com.serhiitymoshenko.organizer.data.di.modules.ui.children.contactsModule
import com.serhiitymoshenko.organizer.data.di.modules.ui.children.coroutinesModule
import com.serhiitymoshenko.organizer.data.di.modules.ui.children.todoModule
import org.koin.dsl.module

val uiModule = module {

    includes(contactsModule, coroutinesModule, todoModule)
}