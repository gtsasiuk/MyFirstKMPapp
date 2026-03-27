package com.example.myfirstkmpapp.ui.screens.datepicker

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
fun DatePickerScreen() {
    var selectedDate by remember { mutableStateOf("") }
    var showPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showPicker = true }) {
            Text(if (selectedDate.isEmpty()) "Pick a date" else selectedDate)
        }

        if (showPicker) {
            DatePickerDialog(
                onDismiss = { showPicker = false },
                onDateSelected = {
                    selectedDate = it
                    showPicker = false
                }
            )
        }
    }
}

@Composable
fun DatePickerDialog(onDismiss: () -> Unit, onDateSelected: (String) -> Unit) {
    val days = (1..31).toList()
    val months = (1..12).toList()
    val years = (2020..2030).toList()

    var selectedDay by remember { mutableStateOf(days.first()) }
    var selectedMonth by remember { mutableStateOf(months.first()) }
    var selectedYear by remember { mutableStateOf(years.first()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pick a date", style = MaterialTheme.typography.titleMedium) },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Day", fontSize = 14.sp, color = Color.Gray)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(days) { day ->
                        DateItem(day, day == selectedDay) { selectedDay = it }
                    }
                }

                Text("Month", fontSize = 14.sp, color = Color.Gray)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(months) { month ->
                        DateItem(month, month == selectedMonth) { selectedMonth = it }
                    }
                }

                Text("Year", fontSize = 14.sp, color = Color.Gray)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(years) { year ->
                        DateItem(year, year == selectedYear) { selectedYear = it }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onDateSelected("$selectedDay/$selectedMonth/$selectedYear") }
            ) {
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
fun DateItem(value: Int, selected: Boolean, onClick: (Int) -> Unit) {
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
            text = "$value",
            color = if (selected) MaterialTheme.colorScheme.primary else Color.Black,
            fontSize = 16.sp
        )
    }
}