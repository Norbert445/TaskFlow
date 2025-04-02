package com.example.taskflow.domain.usecase

import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.models.Todo

class ToggleTodoUseCase(private val todoRepository: TodoRepository) {

    suspend operator fun invoke(todo: Todo, isDone: Boolean) =
        todoRepository.addTodo(todo.copy(isDone = isDone))
}