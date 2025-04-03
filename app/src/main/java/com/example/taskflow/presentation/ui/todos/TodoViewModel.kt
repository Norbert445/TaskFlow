package com.example.taskflow.presentation.ui.todos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflow.domain.model.Todo
import com.example.taskflow.domain.usecase.todo.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.todo.GetTodosUseCase
import com.example.taskflow.domain.usecase.todo.ToggleTodoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber.Forest.d
import timber.log.Timber.Forest.e

class TodoViewModel(
    getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todos = mutableStateOf<List<Todo>>(emptyList())
        private set

    var isLoading = mutableStateOf(true)
        private set

    private val _error = Channel<Exception>()
    val error = _error.receiveAsFlow()

    init {
        viewModelScope.launch {
            try {
                getTodosUseCase().collect {
                    todos.value = it
                    isLoading.value = false

                    d("Got todos: $it")
                }
            } catch (e: Exception) {
                _error.send(e)
                e("Cannot get todos: ${e.localizedMessage}")
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                deleteTodoUseCase(todo)
                d("Deleted todo: $todo")
            } catch (e: Exception) {
                _error.send(e)
                e("Cannot delete todo: ${e.localizedMessage}")
            }
        }
    }

    fun toggleTodo(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            try {
                toggleTodoUseCase(todo, isDone)
                d("Toggled todo: $todo to isDone: $isDone")
            } catch (e: Exception) {
                _error.send(e)
                e("Cannot toggle todo: ${e.localizedMessage}")
            }
        }
    }
}