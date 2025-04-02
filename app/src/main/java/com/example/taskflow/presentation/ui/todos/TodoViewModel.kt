package com.example.taskflow.presentation.ui.todos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.models.Todo
import com.example.taskflow.domain.usecase.todo.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.todo.GetTodosUseCase
import com.example.taskflow.domain.usecase.todo.ToggleTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todos = mutableStateOf<List<Todo>>(emptyList())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            getTodosUseCase().collect {
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