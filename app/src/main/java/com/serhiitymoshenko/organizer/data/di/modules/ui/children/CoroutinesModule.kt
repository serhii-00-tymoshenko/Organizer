package com.serhiitymoshenko.organizer.data.di.modules.ui.children

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val coroutinesModule = module {

    single<CoroutineContext> { Dispatchers.IO + SupervisorJob() }

    single<CoroutineScope> { CoroutineScope(get()) }
}