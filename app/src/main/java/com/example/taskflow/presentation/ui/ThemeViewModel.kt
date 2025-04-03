package com.example.taskflow.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.usecase.user_preferences.GetDarkModeUseCase
import com.example.taskflow.domain.usecase.user_preferences.SetDarkModeUseCase
import kotlinx.coroutines.launch

class ThemeViewModel(
    getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase
) : ViewModel() {
    var darkModeEnabled = mutableStateOf<Boolean?>(null)
        private set

    init {
        viewModelScope.launch {
            getDarkModeUseCase().collect {
                darkModeEnabled.value = it
            }
        }
    }

    fun toggleDarkMode(darkMode: Boolean) {
        viewModelScope.launch {
            setDarkModeUseCase(darkMode)
        }
    }
}