package com.serhiitymoshenko.organizer.ui.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.serhiitymoshenko.organizer.data.datastore.PreferencesKeys
import com.serhiitymoshenko.organizer.data.datastore.appDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val context: Context) {

    fun getAppDataStoreFlow(): Flow<Boolean> = context.appDataStore.data.map { preferences ->
        preferences[PreferencesKeys.IS_FIRST_LAUNCH_KEY] ?: true
    }

    suspend fun changeIsFirstLaunchToFalse() = context.appDataStore.edit { preferences ->
        preferences[PreferencesKeys.IS_FIRST_LAUNCH_KEY] = false
    }
}