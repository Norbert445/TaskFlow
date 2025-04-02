package com.example.taskflow.domain.usecase.user_preferences

import com.example.taskflow.data.repository.user_preferences.UserPreferencesRepository

class GetDarkModeUseCase(private val repository: UserPreferencesRepository) {
    operator fun invoke() = repository.getDarkMode()
}