package com.sexadventure.presentation.addpose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddPoseRoot(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AddPoseScreen(
        onBackClick = onBackClick,
        modifier = modifier,
    )
}
