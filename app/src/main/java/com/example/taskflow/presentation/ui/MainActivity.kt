package com.example.taskflow.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskflow.presentation.ui.theme.TaskFlowTheme
import com.example.taskflow.presentation.ui.todos.TodosScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskFlowTheme {
                Scaffold(
                    contentColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentWindowInsets = WindowInsets.safeDrawing,
                ) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController,
                        startDestination = Todos,
                    ) {
                        composable<Todos> { TodosScreen(innerPadding) }
                    }
                }
            }
        }
    }

    @Serializable
    object Todos
}