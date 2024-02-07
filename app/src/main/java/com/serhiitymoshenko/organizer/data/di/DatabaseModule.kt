package com.serhiitymoshenko.organizer.data.di

import androidx.room.Room
import com.serhiitymoshenko.organizer.data.db.AppDatabase
import com.serhiitymoshenko.organizer.data.db.callback.AppDatabaseCallback
import com.serhiitymoshenko.organizer.data.db.daos.ContactsDao
import com.serhiitymoshenko.organizer.data.db.daos.TasksDao
import com.serhiitymoshenko.organizer.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext().applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).addCallback(AppDatabaseCallback())
            .build()
    }

    single<TasksDao> {
        get<AppDatabase>().tasksDao()
    }

    single<ContactsDao> {
        get<AppDatabase>().contactsDao()
    }
}