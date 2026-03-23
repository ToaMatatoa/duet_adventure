package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllPosesRoot(
    onPoseClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<AllPosesViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    AllPosesScreen(
        state = state,
        onPoseClick = onPoseClick,
        modifier = modifier,
    )
}
