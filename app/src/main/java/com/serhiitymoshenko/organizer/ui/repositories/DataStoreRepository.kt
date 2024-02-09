package com.serhiitymoshenko.organizer.ui.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.serhiitymoshenko.organizer.utils.datastore.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

class DataStoreRepository : KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    private val dataStore = scope.get<DataStore<Preferences>>()

    fun getAppDataStoreFlow(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.IS_FIRST_LAUNCH_KEY] ?: true
    }

    suspend fun changeIsFirstLaunchToFalse() = dataStore.edit { preferences ->
        preferences[PreferencesKeys.IS_FIRST_LAUNCH_KEY] = false
    }
}