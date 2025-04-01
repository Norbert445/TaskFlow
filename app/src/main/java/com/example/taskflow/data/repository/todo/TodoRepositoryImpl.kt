package com.example.taskflow.data.repository.todo

import com.example.taskflow.data.database.dao.TodoDao
import com.example.taskflow.domain.models.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {
    override fun listenForTodoChanges(): Flow<List<Todo>> = todoDao.listenForTodoChanges()
    override suspend fun addTodo(todo: Todo) = todoDao.insertTodo(todo)
}