package com.sexadventure.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileRoot(
    onOpenPoseDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LifecycleResumeEffect(Unit) {
        if (state.pose != null) {
            viewModel.loadPose(state.pose.id)
        }

        onPauseOrDispose { }
    }

    ProfileScreen(
        state = state,
        onGetRandomPose = viewModel::getRandomPose,
        onFavouriteClick = viewModel::toggleFavourite,
        onOpenPoseDetails = onOpenPoseDetails,
        onRemovePose = viewModel::onRemovePose,
        modifier = modifier,
    )
}
