package com.example.taskflow.presentation.ui.todos

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.taskflow.R
import com.example.taskflow.domain.model.Todo
import com.example.taskflow.presentation.ui.swipeable_item.ActionItem
import com.example.taskflow.presentation.ui.swipeable_item.SwipeableItemWithActions

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: (todo: Todo) -> Unit,
    onToggle: (todo: Todo, isDone: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete task?") },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDelete(todo)
                }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    SwipeableItemWithActions(
        actions = {
            ActionItem(
                onClick = { showDeleteDialog = true },
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                icon = Icons.Default.Delete,
                text = stringResource(R.string.task_delete_title),
                modifier = Modifier,
                tint = MaterialTheme.colorScheme.onErrorContainer,
                contentDescription = "Delete todo"
            )
        },
        modifier = modifier.padding(vertical = 8.dp),
    ) {
        val animatedBackgroundColor =
            animateColorAsState(if (todo.isDone) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceContainer)
        Row(
            modifier = Modifier
                .background(animatedBackgroundColor.value)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = todo.isDone,
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.tertiary),
                onCheckedChange = {
                    onToggle(todo, it)
                })

            Text(
                todo.title,
                color = if (todo.isDone) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge.copy(textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None)
            )
        }
    }
}