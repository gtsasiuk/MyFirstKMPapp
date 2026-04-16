package com.example.myfirstkmpapp.ui.shared.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
@OptIn(ExperimentalMaterial3Api::class)
actual fun AddTimeZoneDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable (() -> Unit)
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        content()
    }
}