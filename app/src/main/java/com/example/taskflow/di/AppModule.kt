package com.example.taskflow.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.taskflow.data.database.AppDatabase
import com.example.taskflow.data.database.DATABASE_NAME
import com.example.taskflow.data.database.dao.TodoDao
import com.example.taskflow.data.repository.todo.TodoRepository
import com.example.taskflow.data.repository.todo.TodoRepositoryImpl
import com.example.taskflow.data.repository.user_preferences.UserPreferencesRepository
import com.example.taskflow.data.repository.user_preferences.UserPreferencesRepositoryImpl
import com.example.taskflow.domain.usecase.todo.AddTodoUseCase
import com.example.taskflow.domain.usecase.todo.DeleteTodoUseCase
import com.example.taskflow.domain.usecase.todo.GetTodosUseCase
import com.example.taskflow.domain.usecase.todo.ToggleTodoUseCase
import com.example.taskflow.domain.usecase.user_preferences.GetDarkModeUseCase
import com.example.taskflow.domain.usecase.user_preferences.SetDarkModeUseCase
import com.example.taskflow.presentation.ui.ThemeViewModel
import com.example.taskflow.presentation.ui.create_todo.CreateTodoViewModel
import com.example.taskflow.presentation.ui.todos.TodoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }
    single<TodoDao> { get<AppDatabase>().todoDao() }

    single<DataStore<Preferences>> { androidApplication().dataStore }

    single<TodoRepository> { TodoRepositoryImpl(get()) }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }

    factory { GetTodosUseCase(get()) }
    factory { AddTodoUseCase(get()) }
    factory { DeleteTodoUseCase(get()) }
    factory { ToggleTodoUseCase(get()) }
    factory { SetDarkModeUseCase(get()) }
    factory { GetDarkModeUseCase(get()) }

    viewModel { TodoViewModel(get(), get(), get()) }
    viewModel { CreateTodoViewModel(get()) }
    viewModel { ThemeViewModel(get(), get()) }
}