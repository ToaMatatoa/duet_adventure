package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllPosesRoot(
    onPoseClick: (Int) -> Unit,
    onOpenAddPoseScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<AllPosesViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value

    LifecycleResumeEffect(Unit) {
        viewModel.updateSearchPoseQuery("")
        onPauseOrDispose { }
    }

    AllPosesScreen(
        state = state,
        searchQuery = searchQuery,
        onPoseClick = onPoseClick,
        onOpenAddPoseScreen = onOpenAddPoseScreen,
        onFavouriteClick = viewModel::toggleFavourite,
        onSearchQueryChange = viewModel::updateSearchPoseQuery,
        onCategorySelect = viewModel::selectCategory,
        modifier = modifier,
    )
}
