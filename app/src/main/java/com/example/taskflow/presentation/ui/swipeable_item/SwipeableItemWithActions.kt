package com.example.taskflow.presentation.ui.swipeable_item

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import com.example.taskflow.presentation.ui.theme.Dimens
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeableItemWithActions(
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier,
    onExpanded: () -> Unit = {},
    onCollapsed: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var contextMenuWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember {
        Animatable(initialValue = 0f)
    }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(Dimens.mediumPadding))
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .onSizeChanged {
                    contextMenuWidth = it.width.toFloat()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput(contextMenuWidth) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset =
                                    (offset.value + dragAmount).coerceIn(0f, contextMenuWidth)
                                offset.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value >= contextMenuWidth / 2 -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                        onExpanded()
                                    }
                                }

                                else -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                        onCollapsed()
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            content()
        }
    }
}
