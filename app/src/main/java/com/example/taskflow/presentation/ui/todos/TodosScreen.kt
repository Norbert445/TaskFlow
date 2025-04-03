package com.example.taskflow.presentation.ui.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.taskflow.R
import com.example.taskflow.domain.model.Todo
import com.example.taskflow.presentation.ui.theme.Dimens
import com.example.taskflow.presentation.ui.theme.TaskFlowTheme


@Composable
fun TodosScreen(
    innerPadding: PaddingValues = PaddingValues(),
    todos: List<Todo>,
    isLoading: Boolean,
    darkModeEnabled: Boolean,
    onToggleTodo: (todo: Todo, isDone: Boolean) -> Unit,
    onDeleteTodo: (todo: Todo) -> Unit,
    onDarkModeToggle: (darkMode: Boolean) -> Unit,
) {
    Column(Modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = innerPadding.calculateTopPadding() + Dimens.largePadding,
                    end = Dimens.mediumPadding,
                    start = Dimens.mediumPadding
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.todo_list_title),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall,
            )

            IconButton(onClick = {
                onDarkModeToggle(!darkModeEnabled)
            }) {
                Icon(
                    painter = if (darkModeEnabled) painterResource(R.drawable.ic_light_mode) else painterResource(
                        R.drawable.ic_dark_mode
                    ),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Toggle light/dark mode",
                    modifier = Modifier.size(Dimens.smallIconSize)
                )
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(Dimens.circularProgressWidth)
                        .padding(bottom = Dimens.largePadding + innerPadding.calculateBottomPadding()),
                )
            }
            return@Column
        }

        if (todos.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = Dimens.largePadding + innerPadding.calculateBottomPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_no_data),
                    contentDescription = "No data image",
                )
                Text(
                    stringResource(R.string.tasks_empty_title),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                    start = Dimens.mediumPadding,
                    end = Dimens.mediumPadding,
                    bottom = innerPadding.calculateBottomPadding()
                )
            ) {
                val (incompleteTodos, completedTodos) = todos.partition { !it.isDone }

                if (incompleteTodos.isNotEmpty()) {
                    item {
                        Text(
                            stringResource(R.string.tasks_to_do_title),
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.secondary),
                            modifier = Modifier.padding(vertical = Dimens.smallPadding)
                        )
                    }

                    items(incompleteTodos, key = { it.id }) {
                        TodoItem(it, onDelete = {
                            onDeleteTodo(it)
                        }, onToggle = { todo, isDone ->
                            onToggleTodo(todo, isDone)
                        }, modifier = Modifier.animateItem())
                    }
                }

                if (completedTodos.isNotEmpty()) {
                    item {
                        Text(
                            stringResource(R.string.tasks_completed_title),
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                            modifier = Modifier.padding(
                                top = if (incompleteTodos.isNotEmpty()) Dimens.mediumPadding else Dimens.smallPadding,
                                bottom = Dimens.smallPadding
                            )
                        )
                    }

                    items(completedTodos, key = { it.id }) {
                        TodoItem(it, onDelete = {
                            onDeleteTodo(it)
                        }, onToggle = { todo, isDone ->
                            onToggleTodo(todo, isDone)
                        }, modifier = Modifier.animateItem())
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
fun TodosScreenPreview() {
    TaskFlowTheme {
        TodosScreen(
            PaddingValues(),
            listOf(
                Todo(id = 1, title = "Walk the dog"),
                Todo(id = 2, title = "Go to the gym", isDone = true)
            ),
            false,
            false,
            { _, _ -> },
            {},
            {})
    }
}