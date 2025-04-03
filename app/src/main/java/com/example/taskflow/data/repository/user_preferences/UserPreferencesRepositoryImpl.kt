package com.example.taskflow.data.repository.user_preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {
    override suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit {
            it[darkModeKey] = enabled
        }
    }

    override fun getDarkMode(): Flow<Boolean?> = dataStore.data.map { it[darkModeKey] }

    companion object {
        private val darkModeKey = booleanPreferencesKey("darkMode")
    }
}