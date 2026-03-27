package com.example.myfirstkmpapp.ui.screens.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimePickerScreen() {
    var selectedTime by remember { mutableStateOf("") }
    var showPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showPicker = true }) {
            Text(if (selectedTime.isEmpty()) "Pick a time" else selectedTime)
        }

        if (showPicker) {
            TimePickerDialog(
                onDismiss = { showPicker = false },
                onTimeSelected = {
                    selectedTime = it
                    showPicker = false
                }
            )
        }
    }
}

@Composable
fun TimePickerDialog(onDismiss: () -> Unit, onTimeSelected: (String) -> Unit) {
    val hours = (0..23).toList()
    val minutes = (0..59 step 5).toList() // крок 5 хвилин

    var selectedHour by remember { mutableStateOf(hours.first()) }
    var selectedMinute by remember { mutableStateOf(minutes.first()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pick a time", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hour", fontSize = 14.sp, color = Color.Gray)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(hours) { hour ->
                        TimeItem(hour, hour == selectedHour) { selectedHour = it }
                    }
                }

                Text("Minute", fontSize = 14.sp, color = Color.Gray)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(minutes) { minute ->
                        TimeItem(minute, minute == selectedMinute) { selectedMinute = it }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onTimeSelected("${selectedHour}:${selectedMinute}") }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun TimeItem(value: Int, selected: Boolean, onClick: (Int) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 2.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onClick(value) }
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            color = if (selected) MaterialTheme.colorScheme.primary else Color.Black,
            fontSize = 16.sp
        )
    }
}