package com.example.myfirstkmpapp.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import myfirstkmpapp.composeapp.generated.resources.Res
import myfirstkmpapp.composeapp.generated.resources.buttons
import myfirstkmpapp.composeapp.generated.resources.checkboxes
import myfirstkmpapp.composeapp.generated.resources.chips
import myfirstkmpapp.composeapp.generated.resources.switches
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen (
    onButtonsClicked: () -> Unit,
    onCheckboxesClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onSwitchesClicked: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onButtonsClicked()
            }
        ) {
            Text(stringResource(Res.string.buttons))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onCheckboxesClicked()
            }
        ) {
            Text(stringResource(Res.string.checkboxes))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onChipsClicked()
            }
        ) {
            Text(stringResource(Res.string.chips))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSwitchesClicked()
            }
        ) {
            Text(stringResource(Res.string.switches))
        }
    }
}


@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        {}, {}, {}
    ) {}
}