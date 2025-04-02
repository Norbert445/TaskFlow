package com.example.taskflow.data.repository.todo

import com.example.taskflow.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTodoRepository : TodoRepository {

    private val todos = mutableListOf<Todo>()

    override fun getTodos(): Flow<List<Todo>> {
        return flow { emit(todos) }
    }

    override suspend fun addTodo(todo: Todo) {
        todos.removeIf { it.id == todo.id }
        todos.add(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todos.remove(todo)
    }
}