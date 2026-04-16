package com.example.myfirstkmpapp

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.*
import com.example.myfirstkmpapp.ui.shared_mobile.main.MainScreen

data class AppWindow(
    val state: WindowState,
    val title: String
)

fun main() = application {

    val windows = remember { SnapshotStateList<AppWindow>() }
    var windowCounter by remember { mutableStateOf(1) }

    fun createWindow() {
        windows.add(
            AppWindow(
                state = WindowState(),
                title = "MyFirstKMPapp #$windowCounter"
            )
        )
        windowCounter++
    }

    if (windows.isEmpty()) {
        createWindow()
    }

    windows.forEachIndexed { index, window ->

        Window(
            onCloseRequest = {
                windows.removeAt(index)
                if (windows.isEmpty()) exitApplication()
            },
            title = window.title,
            state = window.state
        ) {

            MenuBar {

                Menu("File") {

                    Item(
                        "New Window",
                        shortcut = KeyShortcut(Key.N, ctrl = true),
                        onClick = { createWindow() }
                    )

                    Item(
                        "Close Window",
                        shortcut = KeyShortcut(Key.W, ctrl = true),
                        onClick = {
                            windows.removeAt(index)
                            if (windows.isEmpty()) exitApplication()
                        }
                    )

                    Separator()

                    Item(
                        "Exit",
                        shortcut = KeyShortcut(Key.Q, ctrl = true),
                        onClick = { exitApplication() }
                    )
                }

                Menu("Edit") {
                    Item("Cut", onClick = {}, shortcut = KeyShortcut(Key.X, ctrl = true))
                    Item("Copy", onClick = {}, shortcut = KeyShortcut(Key.C, ctrl = true))
                    Item("Paste", onClick = {}, shortcut = KeyShortcut(Key.V, ctrl = true))
                }
            }

            MainScreen()
        }
    }
}