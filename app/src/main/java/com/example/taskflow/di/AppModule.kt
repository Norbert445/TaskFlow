package com.example.taskflow.di

import androidx.room.Room
import com.example.taskflow.data.database.AppDatabase
import com.example.taskflow.data.database.DATABASE_NAME
import com.example.taskflow.data.database.dao.TodoDao
import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.data.repository.todo.TodoRepositoryImpl
import com.example.taskflow.domain.usecase.AddTodoUseCase
import com.example.taskflow.domain.usecase.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.ListenForTodoChangesUseCase
import com.example.taskflow.presentation.ui.create_todo.CreateTodoViewModel
import com.example.taskflow.presentation.ui.todos.TodoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }
    single<TodoDao> { get<AppDatabase>().todoDao() }

    single<TodoRepository> { TodoRepositoryImpl(get()) }

    factory { ListenForTodoChangesUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { DeleteTodoUseCase(get()) }

    viewModel { TodoViewModel(get(), get()) }
    viewModel { CreateTodoViewModel(get()) }
}