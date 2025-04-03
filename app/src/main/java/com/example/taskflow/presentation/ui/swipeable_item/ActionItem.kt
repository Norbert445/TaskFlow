package com.example.taskflow.presentation.ui.swipeable_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.taskflow.presentation.ui.theme.Dimens

@Composable
fun ActionItem(
    onClick: () -> Unit,
    backgroundColor: Color,
    icon: ImageVector,
    text: String,
    modifier: Modifier,
    tint: Color = MaterialTheme.colorScheme.background,
    contentDescription: String
) {
    Row(
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .fillMaxHeight()
            .padding(end = Dimens.smallPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.padding(end = Dimens.miniPadding, start = Dimens.mediumPadding)
        )

        Text(text, color = tint, modifier = Modifier.padding(end = Dimens.mediumPadding))
    }
}