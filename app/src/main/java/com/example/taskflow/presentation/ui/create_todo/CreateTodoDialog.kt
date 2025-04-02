package com.example.taskflow.presentation.ui.create_todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTodoDialog(createTodoViewModel: CreateTodoViewModel = koinViewModel()) {
    val todoTitle = createTodoViewModel.todoTitle

    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = todoTitle.value,
            onValueChange = { todoTitle.value = it },
            label = { Text("Enter a task") }
        )

        Button(
            onClick = {
                createTodoViewModel.addTodo()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = todoTitle.value.isNotEmpty()
        ) {
            Text("Add")
        }
    }
}