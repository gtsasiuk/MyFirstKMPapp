package com.example.myfirstkmpapp.ui.shared.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    range: IntRange = 0..23
) {
    val coroutineScope = rememberCoroutineScope()

    val itemHeight = 40.dp
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }

    val offset = remember { Animatable(0f) }

    fun getValueFromOffset(): Int {
        val delta = (offset.value / itemHeightPx).toInt()
        return (value - delta).coerceIn(range)
    }

    Column(
        modifier = modifier
            .wrapContentSize()
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    coroutineScope.launch {
                        offset.snapTo(offset.value + delta)
                    }
                },
                onDragStopped = {
                    coroutineScope.launch {
                        val newValue = getValueFromOffset()
                        onValueChange(newValue)
                        offset.snapTo(0f)
                    }
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(onClick = {
            onValueChange((value + 1).coerceIn(range))
        }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Up")
        }

        Text(
            text = value.toString(),
            style = MaterialTheme.typography.headlineMedium
        )

        IconButton(onClick = {
            onValueChange((value - 1).coerceIn(range))
        }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Down")
        }
    }
}