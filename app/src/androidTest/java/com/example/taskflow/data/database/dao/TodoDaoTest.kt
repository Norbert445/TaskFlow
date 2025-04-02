package com.example.taskflow.data.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.taskflow.data.database.AppDatabase
import com.example.taskflow.domain.models.Todo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class TodoDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var todoDao: TodoDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        todoDao = db.todoDao()
    }

    @After
    fun closeDatabase() {
        db.close()
    }

    @Test
    fun insertTodo() = runTest {
        val todo = Todo(
            id = 1,
            title = "Clean up"
        )

        todoDao.insertTodo(todo)

        val todos = todoDao.getTodos().first()
        assert(todos.contains(todo))
    }

    @Test
    fun deleteTodo() = runTest {
        val todo = Todo(
            id = 1,
            title = "Clean up"
        )

        todoDao.insertTodo(todo)
        todoDao.deleteTodo(todo)

        val todos = todoDao.getTodos().first()
        assert(!todos.contains(todo))
    }

    @Test
    fun getTodos() = runTest {
        val todos = listOf(
            Todo(id = 1, title = "Task 1", isDone = true, updatedAt = 1000L),
            Todo(id = 2, title = "Task 2", isDone = false, updatedAt = 2000L),
            Todo(id = 3, title = "Task 3", isDone = false, updatedAt = 3000L),
            Todo(id = 4, title = "Task 4", isDone = true, updatedAt = 4000L)
        )

        todos.forEach {
            todoDao.insertTodo(it)
        }

        val result = todoDao.getTodos().first()

        assertEquals(listOf(2, 3, 4, 1), result.map { it.id })
    }
}