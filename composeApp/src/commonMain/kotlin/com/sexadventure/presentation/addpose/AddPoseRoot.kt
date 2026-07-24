package com.sexadventure.presentation.addpose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sexadventure.snackbar.SnackbarController
import com.sexadventure.snackbar.SnackbarEvent
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AddPoseRoot(
    poseId: Int,
    onBackClick: () -> Unit,
    onNavigateToAllPoses: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<AddPoseViewModel>(
        parameters = { parametersOf(poseId) },
    )
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val scope = rememberCoroutineScope()
    val backClick by rememberUpdatedState(newValue = onBackClick)
    val navigateToAllPoses by rememberUpdatedState(newValue = onNavigateToAllPoses)

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is AddPoseEffects.BackToHome -> {
                    backClick()
                }

                is AddPoseEffects.NavigateToAllPoses -> {
                    navigateToAllPoses()
                }

                is AddPoseEffects.ShowMessage -> {
                    scope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(message = effect.message),
                        )
                    }
                }
            }
        }
    }

    AddPoseScreen(
        state = state,
        loadImage = viewModel::loadImage,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onCategoryClick = viewModel::onCategoryClick,
        onDifficultyChange = viewModel::onDifficultyChange,
        onPersonalScoreChange = viewModel::onPersonalScoreChange,
        onUserCommentsChange = viewModel::onUserCommentChange,
        onImageSelected = viewModel::onImageSelected,
        onImageRemoved = viewModel::onImageRemoved,
        onBackClick = onBackClick,
        onSavePoseClick = viewModel::savePose,
        modifier = modifier,
    )
}
