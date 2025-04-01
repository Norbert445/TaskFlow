package com.example.taskflow.presentation.ui.todos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.models.Todo
import com.example.taskflow.domain.usecase.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.ListenForTodoChangesUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    listenForTodoChangesUseCase: ListenForTodoChangesUseCase,
    val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    var todos = mutableStateOf<List<Todo>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            listenForTodoChangesUseCase().collect {
                todos.value = it
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }
}