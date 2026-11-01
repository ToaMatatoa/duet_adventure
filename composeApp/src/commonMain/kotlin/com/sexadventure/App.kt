package com.sexadventure

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sexadventure.designsystem.theme.SexAdventureTheme
import com.sexadventure.navigation.NavigationRoot

@Composable
@Preview
fun App() {
    SexAdventureTheme {
        NavigationRoot()
    }
}
