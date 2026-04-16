package com.example.myfirstkmpapp.ui.shared_mobile.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.myfirstkmpapp.ui.shared.dialogs.AddTimeZoneDialog
import com.example.myfirstkmpapp.ui.theme.AppTheme

@Composable
fun MainScreen () {
    AppTheme {
        var selectedPage by remember { mutableStateOf(0) }
        val timeZones = remember { mutableStateListOf<String>() }
        var showDialog by remember { mutableStateOf(false) }

        Scaffold(

            floatingActionButton = {
                if (selectedPage == 0) {
                    FloatingActionButton(onClick = {
                        showDialog = true
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            },

            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedPage == 0,
                        onClick = { selectedPage = 0 },
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text("Zones") }
                    )
                    NavigationBarItem(
                        selected = selectedPage == 1,
                        onClick = { selectedPage = 1 },
                        icon = { Icon(Icons.Default.Place, null) },
                        label = { Text("Meeting") }
                    )
                }
            }

        ) { padding ->

            Box(modifier = Modifier.padding(padding)) {

                when (selectedPage) {
                    0 -> TimeZonesPage(timeZones)
                    1 -> FindMeetingPage(timeZones)
                }

                if (showDialog) {
                    AddTimeZoneDialog(
                        onAdd = {
                            timeZones.addAll(it.filter { zone -> !timeZones.contains(zone) })
                            showDialog = false
                        },
                        onDismiss = { showDialog = false }
                    )
                }
            }
        }
    }
}


