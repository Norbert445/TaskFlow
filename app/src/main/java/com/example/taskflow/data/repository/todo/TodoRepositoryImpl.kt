package com.example.taskflow.data.repository.todo

import com.example.taskflow.data.database.dao.TodoDao
import com.example.taskflow.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {
    override fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()
    override suspend fun addTodo(todo: Todo) = todoDao.insertTodo(todo)
    override suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)
}