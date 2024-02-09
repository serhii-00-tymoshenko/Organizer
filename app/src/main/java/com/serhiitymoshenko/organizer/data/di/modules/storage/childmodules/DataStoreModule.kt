package com.serhiitymoshenko.organizer.data.di.modules.storage.childmodules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.serhiitymoshenko.organizer.utils.DATA_STORE_NAME
import org.koin.dsl.module

val dataStoreModule = module {

    single<DataStore<Preferences>> {
       PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(get(), DATA_STORE_NAME)),
            scope = get(),
            produceFile = { get<Context>().preferencesDataStoreFile(DATA_STORE_NAME) }
        )
    }
}