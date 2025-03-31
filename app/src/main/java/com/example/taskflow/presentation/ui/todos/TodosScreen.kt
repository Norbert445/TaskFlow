package com.example.taskflow.presentation.ui.todos

import TodoItem
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodosScreen(todoViewModel: TodoViewModel = koinViewModel()) {
    val todos = todoViewModel.todos

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        items(todos.value.size) {
            TodoItem()
        }
    }
}