package com.example.myfirstkmpapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import myfirstkmpapp.composeapp.generated.resources.Res
import myfirstkmpapp.composeapp.generated.resources.compose_multiplatform
import co.touchlab.kermit.Logger
import com.example.myfirstkmpapp.data.timezone.TimeZoneManagerImpl

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                Logger.i { "Logger test." }
            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                val manager = remember { TimeZoneManagerImpl() }

                val currentTime = manager.getTimeForZone("UTC+2")
                val diffLondonKyiv = manager.getTimeDifference("UTC")
                val isDayInKyiv = manager.isDayTime("UTC+2")
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                    Text(
                        text = "Current Time in Ukraine: $currentTime",
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Difference in time between London and Kyiv: $diffLondonKyiv hours",
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Is this a day time in Kyiv? $isDayInKyiv",
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                }
            }
        }
    }
}