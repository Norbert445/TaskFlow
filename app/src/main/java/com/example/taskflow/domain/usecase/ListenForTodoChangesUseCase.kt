package com.example.taskflow.domain.usecase

import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.models.Todo
import kotlinx.coroutines.flow.Flow

class ListenForTodoChangesUseCase(private val todoRepository: TodoRepository) {

    operator fun invoke(): Flow<List<Todo>> {
        return todoRepository.listenForTodoChanges()
    }
}