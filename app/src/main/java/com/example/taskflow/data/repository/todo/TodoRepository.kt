package com.example.taskflow.data.repository.todo

import com.example.taskflow.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun listenForTodoChanges(): Flow<List<Todo>>
}