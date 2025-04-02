package com.example.taskflow.presentation.ui.todos

import com.example.taskflow.MainDispatcherRule
import com.example.taskflow.data.repository.todo.FakeTodoRepository
import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.domain.models.Todo
import com.example.taskflow.domain.usecase.todo.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.todo.GetTodosUseCase
import com.example.taskflow.domain.usecase.todo.ToggleTodoUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var todoRepository: TodoRepository
    private lateinit var getTodosUseCase: GetTodosUseCase
    private lateinit var deleteTodoUseCase: DeleteTodoUseCase
    private lateinit var toggleTodoUseCase: ToggleTodoUseCase
    private lateinit var viewModel: TodoViewModel

    @Before
    fun setUp() {
        todoRepository = FakeTodoRepository()
        getTodosUseCase = GetTodosUseCase(todoRepository)
        deleteTodoUseCase = DeleteTodoUseCase(todoRepository)
        toggleTodoUseCase = ToggleTodoUseCase(todoRepository)
        viewModel = TodoViewModel(getTodosUseCase, deleteTodoUseCase, toggleTodoUseCase)
    }

    @Test
    fun testTodoToggleToDone() = runTest {
        val todo = Todo(
            id = 1,
            title = "Walk the dog"
        )

        todoRepository.addTodo(todo)

        viewModel.toggleTodo(todo, true)

        val todos = todoRepository.getTodos().first()
        assert(todos.find { it.id == todo.id }?.isDone == true)
    }

    @Test
    fun testTodoToggleToUndone() = runTest {
        val todo = Todo(
            id = 1,
            title = "Walk the dog",
            isDone = true
        )

        todoRepository.addTodo(todo)

        viewModel.toggleTodo(todo, false)

        val todos = todoRepository.getTodos().first()
        assert(todos.find { it.id == todo.id }?.isDone == false)
    }

    @Test
    fun testTodoDeletion() = runTest {
        val todo = Todo(
            id = 1,
            title = "Walk the dog",
            isDone = true
        )

        todoRepository.addTodo(todo)

        viewModel.deleteTodo(todo)

        val todos = todoRepository.getTodos().first()
        assert(todos.isEmpty())
    }
}