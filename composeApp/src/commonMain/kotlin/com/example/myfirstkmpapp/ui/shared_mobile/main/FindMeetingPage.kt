package com.example.myfirstkmpapp.ui.shared_mobile.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstkmpapp.data.timezone.TimeZoneManagerImpl
import com.example.myfirstkmpapp.ui.shared.components.NumberPicker
import com.example.myfirstkmpapp.ui.shared.dialogs.MeetingDialog

@Composable
fun FindMeetingPage(
    timeZones: SnapshotStateList<String>
) {
    val manager = remember { TimeZoneManagerImpl() }

    var start by remember { mutableStateOf(9) }
    var end by remember { mutableStateOf(18) }

    var showDialog by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Select Time Range",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            NumberPicker(value = start, onValueChange = { start = it })

            Spacer(Modifier.width(32.dp))

            NumberPicker(value = end, onValueChange = { end = it })
        }

        Spacer(Modifier.height(24.dp))

        Text(
            "Selected Time Zones",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        if (timeZones.isEmpty()) {
            Text(
                "No time zones selected",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(timeZones) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            it.substringAfter("/"),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                result = manager.findSuitableHours(start, end, timeZones)
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Find Meeting Time")
        }

        if (showDialog) {
            MeetingDialog(result) {
                showDialog = false
            }
        }
    }
}