package com.example.taskflow.presentation.ui.create_todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.usecase.AddTodoUseCase
import kotlinx.coroutines.launch

class CreateTodoViewModel(val addTodoUseCase: AddTodoUseCase) : ViewModel() {
    var todoTitle = mutableStateOf("")

    fun addTodo() {
        viewModelScope.launch {
            addTodoUseCase(todoTitle.value)
        }
    }
}