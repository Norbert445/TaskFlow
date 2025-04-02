package com.example.taskflow.presentation.ui.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskflow.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun TodosScreen(
    todoViewModel: TodoViewModel = koinViewModel(),
    onDarkModeToggle: () -> Unit,
    darkModeEnabled: State<Boolean>
) {
    val todos = todoViewModel.todos

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, end = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "My Todo List",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall,
            )


            IconButton(onClick = {
                onDarkModeToggle()
            }) {
                Icon(
                    painter = if (darkModeEnabled.value) painterResource(R.drawable.ic_light_mode) else painterResource(
                        R.drawable.ic_dark_mode
                    ),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Toggle light/dark mode",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (todos.value.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_no_data),
                    contentDescription = "No data image",
                )
                Text("No todos", color = MaterialTheme.colorScheme.onBackground)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                val (incompleteTodos, completedTodos) = todos.value.partition { !it.isDone }

                if (incompleteTodos.isNotEmpty()) {
                    item {
                        Text(
                            "Tasks to Do",
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(incompleteTodos, key = { it.id }) {
                        TodoItem(it, onDelete = {
                            todoViewModel.deleteTodo(it)
                        }, onToggle = { todo, isDone ->
                            todoViewModel.toggleTodo(todo, isDone)
                        }, modifier = Modifier.animateItem())
                    }
                }

                if (completedTodos.isNotEmpty()) {
                    item {
                        Text(
                            "Completed Tasks",
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                            modifier = Modifier
                                .padding(top = if(incompleteTodos.isNotEmpty()) 16.dp else 8.dp, bottom = 8.dp)
                                .animateItem()
                        )
                    }

                    items(completedTodos, key = { it.id }) {
                        TodoItem(it, onDelete = {
                            todoViewModel.deleteTodo(it)
                        }, onToggle = { todo, isDone ->
                            todoViewModel.toggleTodo(todo, isDone)
                        }, modifier = Modifier.animateItem())
                    }
                }
            }
        }
    }
}