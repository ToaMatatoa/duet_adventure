package com.sexadventure.presentation.addpose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddPoseScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = "Add Pose Screen",
        )
    }
}

@Preview
@Composable
fun AddPoseScreenPreview() {
    AddPoseScreen(
        onBackClick = {},
    )
}
