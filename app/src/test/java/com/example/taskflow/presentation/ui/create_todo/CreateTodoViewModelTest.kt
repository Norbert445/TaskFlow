package com.example.taskflow.presentation.ui.create_todo

import com.example.taskflow.MainDispatcherRule
import com.example.taskflow.data.repository.todo.FakeTodoRepository
import com.example.taskflow.domain.usecase.todo.AddTodoUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateTodoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var todoRepository: FakeTodoRepository
    private lateinit var addTodoUseCase: AddTodoUseCase
    private lateinit var viewModel: CreateTodoViewModel

    @Before
    fun setUp() {
        todoRepository = FakeTodoRepository()
        addTodoUseCase = AddTodoUseCase(todoRepository)
        viewModel = CreateTodoViewModel(addTodoUseCase)
    }

    @Test
    fun testIfAddedTodo() = runTest {
        viewModel.todoTitle.value = "Go to the gym"
        viewModel.addTodo()

        val todos = todoRepository.getTodos().first()
        assert(todos.find { it.title == "Go to the gym" } != null)
    }


    @Test
    fun resetTitleAfterAddedTodo() = runTest {
        viewModel.todoTitle.value = "Walk the dog"
        viewModel.addTodo()

        assert(viewModel.todoTitle.value.isEmpty())
    }
}