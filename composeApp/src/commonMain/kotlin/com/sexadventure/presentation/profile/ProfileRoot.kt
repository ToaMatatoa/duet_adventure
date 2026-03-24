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
        if (state.randomPose != null) {
            viewModel.loadRandomPoseWhenResume(state.randomPose.id)
        }
        if (state.poseOfTheDay != null) {
            viewModel.loadPoseOfTheDayWhenResume(state.poseOfTheDay.id)
        }

        onPauseOrDispose { }
    }

    ProfileScreen(
        state = state,
        onGetRandomPose = viewModel::getRandomPose,
        onTogglePoseOfTheDay = viewModel::togglePoseOfTheDay,
        onFavouriteClick = viewModel::toggleFavourite,
        onOpenPoseDetails = onOpenPoseDetails,
        onRemovePose = viewModel::onRemoveRandomPose,
        modifier = modifier,
    )
}
