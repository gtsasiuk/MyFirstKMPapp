package com.example.myfirstkmpapp.ui.shared.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> AnimatedSwipeDismiss(
    item: T,
    onDismiss: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it != SwipeToDismissBoxValue.Settled) {
                onDismiss(item)
            }
            true
        }
    )

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(),
        exit = shrinkVertically(
            animationSpec = tween(300)
        )
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {},
            content = { content() }
        )
    }
}