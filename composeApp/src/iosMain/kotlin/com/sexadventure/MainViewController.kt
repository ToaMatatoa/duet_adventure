package com.sexadventure

import androidx.compose.ui.window.ComposeUIViewController
import com.sexadventure.di.initKoin

// PascalCase on purpose: Swift calls this as MainViewControllerKt.MainViewController().
@Suppress("ktlint:standard:function-naming", "FunctionNaming")
fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIos() {
    initKoin()
}
