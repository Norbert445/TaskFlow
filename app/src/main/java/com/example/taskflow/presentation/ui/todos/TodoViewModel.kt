package com.example.taskflow.presentation.ui.todos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.models.Todo
import com.example.taskflow.domain.usecase.todo.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.todo.ListenForTodoChangesUseCase
import com.example.taskflow.domain.usecase.todo.ToggleTodoUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TodoViewModel(
    listenForTodoChangesUseCase: ListenForTodoChangesUseCase,
    val deleteTodoUseCase: DeleteTodoUseCase,
    val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todos = mutableStateOf<List<Todo>>(emptyList())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            listenForTodoChangesUseCase().collect {
                todos.value = it
                isLoading.value = false
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun toggleTodo(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            toggleTodoUseCase(todo, isDone)
        }
    }
}