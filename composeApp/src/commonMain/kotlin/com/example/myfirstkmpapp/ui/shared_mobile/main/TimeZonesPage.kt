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
import com.example.myfirstkmpapp.ui.shared.components.AnimatedSwipeDismiss
import kotlinx.coroutines.delay

@Composable
fun TimeZonesPage(
    timeZones: SnapshotStateList<String>
) {
    val manager = remember { TimeZoneManagerImpl() }

    var time by remember { mutableStateOf(manager.currentTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            time = manager.currentTime()
            delay(60000)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        "Your Location",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        manager.currentTimeZone().substringAfter("/"),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        time,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        manager.getDateForZone(manager.currentTimeZone()),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        LazyColumn {
            items(timeZones) { zone ->
                AnimatedSwipeDismiss(
                    item = zone,
                    onDismiss = { timeZones.remove(it) }
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                zone.substringAfter("/"),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(manager.getTimeForZone(zone))
                            Text(manager.getDateForZone(zone))
                        }
                    }
                }
            }
        }
    }
}