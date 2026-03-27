package com.example.myfirstkmpapp.ui.screens.chips


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.example.myfirstkmpapp.ui.theme.AppTheme

@Composable
fun ChipScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            AssistChip(
                onClick = { Logger.i("Assist clicked") },
                label = { Text("Assist chip") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp)
            )

            var selected by remember { mutableStateOf(false) }

            FilterChip(
                onClick = { selected = !selected },
                label = {
                    Text("Filter chip")
                },
                selected = selected,
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier
                                .size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            var enabled by remember { mutableStateOf(true) }

            InputChip(
                onClick = {
                    enabled = !enabled
                },
                label = { Text("Input chip") },
                selected = enabled,
                avatar = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Localized description",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
                modifier = Modifier
                    .padding(8.dp, 0.dp)
            )
            SuggestionChip(
                onClick = { Logger.i("Suggestion chip") },
                label = { Text("Suggestion chip") },
                modifier = Modifier
                    .padding(8.dp, 0.dp)
            )
        }
    }
}

@Composable
@Preview
fun ChipScreenPreview() {
    AppTheme {
        Scaffold {
            ChipScreen()
        }
    }
}