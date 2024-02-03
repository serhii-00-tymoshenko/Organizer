package com.serhiitymoshenko.organizer.data.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val coroutinesModule = module {
    single<CoroutineContext> { Dispatchers.IO + SupervisorJob() }
}