package com.sexadventure

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sexadventure.navigation.NavigationRoot
import com.sexadventure.designsystem.theme.SexAdventureTheme

@Composable
@Preview
fun App() {
    SexAdventureTheme {
        NavigationRoot()
    }
}
