package com.example.myfirstkmpapp.ui.shared.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
@OptIn(ExperimentalMaterial3Api::class)
actual fun MeetingDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable (() -> Unit)
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        content()
    }
}