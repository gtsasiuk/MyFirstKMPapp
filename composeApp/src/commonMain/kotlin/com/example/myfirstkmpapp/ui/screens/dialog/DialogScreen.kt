package com.example.myfirstkmpapp.ui.screens.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var showDialog by remember { mutableStateOf(false) }

        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Dialog Title") },
                text = { Text("This is a sample dialog content.") }
            )
        }
    }
}