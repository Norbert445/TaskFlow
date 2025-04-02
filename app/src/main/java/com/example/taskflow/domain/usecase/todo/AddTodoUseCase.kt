package com.example.taskflow.domain.usecase.todo

import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.model.Todo

class AddTodoUseCase(private val todoRepository: TodoRepository) {

    suspend operator fun invoke(title: String) {
        val todo = Todo(title = title)
        return todoRepository.addTodo(todo)
    }
}