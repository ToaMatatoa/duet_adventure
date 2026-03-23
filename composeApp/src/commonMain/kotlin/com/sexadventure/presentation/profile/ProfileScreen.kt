package com.sexadventure.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.topbar.TopBar

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            title = Strings.Profile.SCREEN_TITLE,
            showBackButton = false,
        )

        Text(
            text = Strings.Common.PLACEHOLDER,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
