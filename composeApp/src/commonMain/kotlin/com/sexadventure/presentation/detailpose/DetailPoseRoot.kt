package com.sexadventure.presentation.detailpose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.snackbar.SnackbarController
import com.sexadventure.snackbar.SnackbarEvent
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailPoseRoot(
    poseId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<DetailPoseViewModel>(
        parameters = { parametersOf(poseId) },
    )
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val scope = rememberCoroutineScope()
    val backClick by rememberUpdatedState(newValue = onBackClick)

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is DetailPoseEffects.BackToHome -> {
                    backClick()
                }

                is DetailPoseEffects.ShowMessage -> {
                    scope.launch {
                        SnackbarController.sendEvent(
                            event = SnackbarEvent(
                                message = Strings.PoseDetail.POSE_DELETED,
                            ),
                        )
                    }
                }
            }
        }
    }

    DetailPoseScreen(
        state = state,
        loadImage = viewModel::loadImage,
        onBackClick = onBackClick,
        onDeletePose = viewModel::deletePose,
        onToggleFavourite = viewModel::toggleFavourite,
        onScoreChange = viewModel::updateScore,
        onDifficultyChange = viewModel::updateDifficulty,
        modifier = modifier,
    )
}
