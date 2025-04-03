package com.example.taskflow.presentation.ui.create_todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.usecase.todo.AddTodoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber.Forest.e

class CreateTodoViewModel(val addTodoUseCase: AddTodoUseCase) : ViewModel() {
    var todoTitle = mutableStateOf("")

    private val _error = Channel<Exception>()
    val error = _error.receiveAsFlow()

    fun addTodo() {
        viewModelScope.launch {
            try {
                addTodoUseCase(todoTitle.value)
                todoTitle.value = ""
            } catch (e: Exception) {
                _error.send(e)
                e("Cannot add todo: ${e.localizedMessage}")
            }
        }
    }
}