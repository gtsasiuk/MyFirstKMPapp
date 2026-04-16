package com.example.myfirstkmpapp.ui.shared.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
actual fun AddTimeZoneDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable (() -> Unit)
) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}