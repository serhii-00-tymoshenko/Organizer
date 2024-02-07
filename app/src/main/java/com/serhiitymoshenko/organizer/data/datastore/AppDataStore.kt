package com.serhiitymoshenko.organizer.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.serhiitymoshenko.organizer.utils.DATA_STORE_NAME

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(
    DATA_STORE_NAME
)

object PreferencesKeys {
    val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch_key")
}