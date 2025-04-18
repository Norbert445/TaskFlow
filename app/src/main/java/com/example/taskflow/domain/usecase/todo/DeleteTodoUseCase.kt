package com.example.taskflow.domain.usecase.todo

import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.model.Todo

class DeleteTodoUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(todo: Todo) = todoRepository.deleteTodo(todo)
}