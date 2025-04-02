package com.example.taskflow.domain.usecase.todo

import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.model.Todo

class ToggleTodoUseCase(private val todoRepository: TodoRepository) {

    suspend operator fun invoke(todo: Todo, isDone: Boolean) =
        todoRepository.addTodo(todo.copy(isDone = isDone, updatedAt = System.currentTimeMillis()))
}