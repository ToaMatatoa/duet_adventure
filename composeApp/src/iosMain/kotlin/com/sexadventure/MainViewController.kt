package com.sexadventure

import androidx.compose.ui.window.ComposeUIViewController
import com.sexadventure.core.di.initKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIos() {
    initKoin()
}

