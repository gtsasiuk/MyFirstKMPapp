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
import myfirstkmpapp.composeapp.generated.resources.datepicker
import myfirstkmpapp.composeapp.generated.resources.switches
import myfirstkmpapp.composeapp.generated.resources.timepicker
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen (
    onButtonsClicked: () -> Unit,
    onCheckboxesClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onDatePickerClicked: () -> Unit,
    onSwitchesClicked: () -> Unit,
    onTimePickerClicked: () -> Unit,
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
                onDatePickerClicked()
            }
        ) {
            Text(stringResource(Res.string.datepicker))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSwitchesClicked()
            }
        ) {
            Text(stringResource(Res.string.switches))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onTimePickerClicked()
            }
        ) {
            Text(stringResource(Res.string.timepicker))
        }
    }
}


@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        {}, {}, {},
        {}, {}
    ) {}
}