package com.example.myfirstkmpapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.myfirstkmpapp.ui.shared_mobile.main.MainScreen
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("@js-joda/timezone")
external object JsJodaTimeZoneModule

private val jsJodaTz = JsJodaTimeZoneModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        MainScreen()
    }
}