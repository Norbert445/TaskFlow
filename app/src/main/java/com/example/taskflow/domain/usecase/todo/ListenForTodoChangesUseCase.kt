package com.example.taskflow.domain.usecase.todo

import com.example.taskflow.data.repository.todo.TodoRepository

class ListenForTodoChangesUseCase(private val todoRepository: TodoRepository) {
    operator fun invoke() = todoRepository.listenForTodoChanges()
}