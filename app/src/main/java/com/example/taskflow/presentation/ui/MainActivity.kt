package com.example.taskflow.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.taskflow.presentation.ui.create_todo.CreateTodoDialog
import com.example.taskflow.presentation.ui.create_todo.CreateTodoViewModel
import com.example.taskflow.presentation.ui.theme.TaskFlowTheme
import com.example.taskflow.presentation.ui.todos.TodoViewModel
import com.example.taskflow.presentation.ui.todos.TodosScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeViewModel: ThemeViewModel by viewModel()

            TaskFlowTheme(darkTheme = themeViewModel.darkModeEnabled.value) {
                val navController = rememberNavController()

                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(CreateTodo)
                            },
                        ) {
                            Icon(Icons.Filled.Add, "Floating action button for adding Todo.")
                        }
                    }) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = Todos
                    ) {
                        composable<Todos> {
                            val todoViewModel: TodoViewModel = koinViewModel()

                            TodosScreen(
                                innerPadding = innerPadding,
                                todos = todoViewModel.todos.value,
                                isLoading = todoViewModel.isLoading.value,
                                darkModeEnabled = themeViewModel.darkModeEnabled.value,
                                onToggleTodo = { todo, isDone ->
                                    todoViewModel.toggleTodo(todo, isDone)
                                },
                                onDeleteTodo = { todo ->
                                    todoViewModel.deleteTodo(todo)
                                },
                                onDarkModeToggle = {
                                    themeViewModel.toggleDarkMode()
                                })
                        }
                        dialog<CreateTodo> {
                            val createTodoViewModel: CreateTodoViewModel = koinViewModel()

                            CreateTodoDialog(createTodoViewModel.todoTitle, onAddTodo = {
                                createTodoViewModel.addTodo()
                            })
                        }
                    }
                }
            }
        }
    }

    @Serializable
    object Todos

    @Serializable
    object CreateTodo
}