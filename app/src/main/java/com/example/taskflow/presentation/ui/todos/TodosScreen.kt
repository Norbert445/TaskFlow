package com.example.taskflow.presentation.ui.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskflow.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodosScreen(todoViewModel: TodoViewModel = koinViewModel()) {
    val todos = todoViewModel.todos

    if (todos.value.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            )
        ) {
            items(todos.value, key = { it.id }) {
                TodoItem(it, onDelete = {
                    todoViewModel.deleteTodo(it)
                }, modifier = Modifier.animateItem())
            }
        }
    }
}