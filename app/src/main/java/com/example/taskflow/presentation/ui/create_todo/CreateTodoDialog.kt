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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.taskflow.R
import com.example.taskflow.presentation.ui.theme.Dimens
import com.example.taskflow.presentation.ui.theme.TaskFlowTheme

@Composable
fun CreateTodoDialog(
    todoTitle: MutableState<String>,
    onAddTodo: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.mediumBorderRadius))
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
                onAddTodo()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.mediumPadding),
            enabled = todoTitle.value.isNotEmpty()
        ) {
            Text(stringResource(R.string.task_add_title))
        }
    }
}

@Composable
@PreviewLightDark
fun CreateTodoDialogPreview() {
    TaskFlowTheme {
        CreateTodoDialog(remember { mutableStateOf("Walk the dog") }) {}
    }
}