package com.sexadventure.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SexAdventureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColorScheme = if (darkTheme) DarkAppColorScheme else LightAppColorScheme

    MaterialTheme(
        colorScheme = appColorScheme,
        typography = AppTypography(),
        content = content,
    )
}
