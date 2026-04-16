package com.example.myfirstkmpapp.ui.shared.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MeetingDialog(
    hours: List<Int>,
    onDismiss: () -> Unit
) {
    MeetingDialogWrapper(onDismiss = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Available Meeting Time",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(16.dp))

                if (hours.isEmpty()) {
                    Text("No suitable time found")
                } else {
                    hours.forEach {
                        Text("$it:00")
                    }
                }

                Spacer(Modifier.height(24.dp))

                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        }
    }
}

@Composable
expect fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)