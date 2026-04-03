package com.sexadventure.presentation.addpose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddPoseRoot(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<AddPoseViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    AddPoseScreen(
        state = state,
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
