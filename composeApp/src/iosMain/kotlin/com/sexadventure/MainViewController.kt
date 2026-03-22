package com.sexadventure

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIos() {
    initKoin()
}
