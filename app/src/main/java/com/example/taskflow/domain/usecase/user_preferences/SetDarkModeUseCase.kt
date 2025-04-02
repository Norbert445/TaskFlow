package com.example.taskflow.domain.usecase.user_preferences

import com.example.taskflow.data.repository.user_preferences.UserPreferencesRepository

class SetDarkModeUseCase(private val repository: UserPreferencesRepository) {
    suspend operator fun invoke(enabled: Boolean) = repository.setDarkMode(enabled)
}