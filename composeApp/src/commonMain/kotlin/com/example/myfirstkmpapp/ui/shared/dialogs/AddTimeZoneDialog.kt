package com.example.myfirstkmpapp.ui.shared.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstkmpapp.data.timezone.TimeZoneManagerImpl

@Composable
fun AddTimeZoneDialog(
    onAdd: (List<String>) -> Unit,
    onDismiss: () -> Unit
) {
    val manager = remember { TimeZoneManagerImpl() }
    val allZones = manager.getAllTimeZones()

    val selectedZones = remember { mutableStateListOf<String>() }

    val filteredZones = allZones.filter {
        it.startsWith("Europe") || it.startsWith("Asia")
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Time Zones") },
        text = {
            LazyColumn(
                modifier = Modifier.height(300.dp)
            ) {
                items(filteredZones) { zone ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (selectedZones.contains(zone)) {
                                    selectedZones.remove(zone)
                                } else {
                                    selectedZones.add(zone)
                                }
                            }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedZones.contains(zone),
                            onCheckedChange = null
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(zone.substringAfter("/"))
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onAdd(selectedZones)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}