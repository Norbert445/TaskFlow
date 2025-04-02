package com.example.taskflow.data.repository.user_preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun setDarkMode(enabled: Boolean)
    fun getDarkMode(): Flow<Boolean>
}