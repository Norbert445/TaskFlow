package com.example.taskflow.domain.usecase.todo

import com.example.taskflow.data.repository.todo.TodoRepository

class GetTodosUseCase(private val todoRepository: TodoRepository) {
    operator fun invoke() = todoRepository.getTodos()
}