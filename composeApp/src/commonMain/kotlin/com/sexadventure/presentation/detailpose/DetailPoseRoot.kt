package com.sexadventure.presentation.detailpose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    DetailPoseScreen(
        state = state,
        onBackClick = onBackClick,
        onToggleFavourite = viewModel::toggleFavourite,
        onScoreChange = viewModel::updateScore,
        onDifficultyChange = viewModel::updateDifficulty,
        modifier = modifier,
    )
}
