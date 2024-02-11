package com.serhiitymoshenko.organizer.data.di.modules.ui

import org.koin.dsl.module

val uiModule = module {

    includes(contactsModule, coroutinesModule, todoModule)
}