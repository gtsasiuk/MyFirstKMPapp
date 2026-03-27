package com.example.myfirstkmpapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfirstkmpapp.ui.screens.buttons.ButtonsScreen
import com.example.myfirstkmpapp.ui.screens.checkboxes.CheckboxesScreen
import com.example.myfirstkmpapp.ui.screens.chips.ChipScreen
import com.example.myfirstkmpapp.ui.screens.datepicker.DatePickerScreen
import com.example.myfirstkmpapp.ui.screens.dialog.DialogScreen
import com.example.myfirstkmpapp.ui.screens.divider.DividerScreen
import com.example.myfirstkmpapp.ui.screens.main.MainScreen
import com.example.myfirstkmpapp.ui.screens.progressbar.ProgressBarScreen
import com.example.myfirstkmpapp.ui.screens.switches.SwitchScreen
import com.example.myfirstkmpapp.ui.screens.timepicker.TimePickerScreen
import kotlinx.coroutines.launch
import myfirstkmpapp.composeapp.generated.resources.Res
import myfirstkmpapp.composeapp.generated.resources.back
import myfirstkmpapp.composeapp.generated.resources.buttons
import myfirstkmpapp.composeapp.generated.resources.checkboxes
import myfirstkmpapp.composeapp.generated.resources.chips
import myfirstkmpapp.composeapp.generated.resources.datepicker
import myfirstkmpapp.composeapp.generated.resources.dialog
import myfirstkmpapp.composeapp.generated.resources.divider
import myfirstkmpapp.composeapp.generated.resources.main
import myfirstkmpapp.composeapp.generated.resources.progressbar
import myfirstkmpapp.composeapp.generated.resources.switches
import myfirstkmpapp.composeapp.generated.resources.timepicker
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class AppScreen(val title: StringResource) {
    Main(title = Res.string.main),
    Buttons(title = Res.string.buttons),
    Checkboxes(title = Res.string.checkboxes),
    Chips(title = Res.string.chips),
    Datepicker(title = Res.string.datepicker),
    Dialog(title = Res.string.dialog),
    Divider(title = Res.string.divider),
    ProgressBar(title = Res.string.progressbar),
    Switches(title = Res.string.switches),
    Timepicker(title = Res.string.timepicker),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar (
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back)
                    )
                }
            }
        })
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Main.name
    )
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onButtonsClicked = { navController.navigate(AppScreen.Buttons.name) },
                    onCheckboxesClicked = { navController.navigate(AppScreen.Checkboxes.name) },
                    onChipsClicked = { navController.navigate(AppScreen.Chips.name) },
                    onDatePickerClicked = { navController.navigate(AppScreen.Datepicker.name) },
                    onDialogClicked = { navController.navigate(AppScreen.Dialog.name) },
                    onDividerClicked = { navController.navigate(AppScreen.Divider.name) },
                    onProgressBarClicked = { navController.navigate(AppScreen.ProgressBar.name) },
                    onSwitchesClicked = { navController.navigate(AppScreen.Switches.name) },
                    onTimePickerClicked = { navController.navigate(AppScreen.Timepicker.name) }
                )
            }
            composable(route = AppScreen.Buttons.name) {
                ButtonsScreen(
                    onFilledButtonClicked = { text ->
                        scope.launch {
                            snackbarHostState
                                .showSnackbar(
                                    message = text,
                                    duration = SnackbarDuration.Short
                                )
                        }
                    }
                )
            }
            composable(route = AppScreen.Checkboxes.name) {
                CheckboxesScreen()
            }
            composable(route = AppScreen.Chips.name) {
                ChipScreen()
            }
            composable(route = AppScreen.Datepicker.name) {
                DatePickerScreen()
            }
            composable(route = AppScreen.Dialog.name) {
                DialogScreen()
            }
            composable(route = AppScreen.Divider.name) {
                DividerScreen()
            }
            composable(route = AppScreen.ProgressBar.name) {
                ProgressBarScreen()
            }
            composable(route = AppScreen.Switches.name) {
                SwitchScreen()
            }
            composable(route = AppScreen.Timepicker.name) {
                TimePickerScreen()
            }
        }
    }
}