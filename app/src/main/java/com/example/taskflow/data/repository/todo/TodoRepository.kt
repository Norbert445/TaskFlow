package com.example.taskflow.data.repository.todo

import com.example.taskflow.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun addTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}