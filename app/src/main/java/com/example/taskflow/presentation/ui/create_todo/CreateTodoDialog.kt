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
import androidx.compose.ui.res.stringResource
import com.example.taskflow.R
import com.example.taskflow.presentation.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTodoDialog(createTodoViewModel: CreateTodoViewModel = koinViewModel()) {
    val todoTitle = createTodoViewModel.todoTitle

    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.mediumPadding))
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.mediumPadding)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = todoTitle.value,
            onValueChange = { todoTitle.value = it },
            label = { Text(stringResource(R.string.task_input_hint)) }
        )

        Button(
            onClick = {
                createTodoViewModel.addTodo()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.mediumPadding),
            enabled = todoTitle.value.isNotEmpty()
        ) {
            Text(stringResource(R.string.task_add_title))
        }
    }
}