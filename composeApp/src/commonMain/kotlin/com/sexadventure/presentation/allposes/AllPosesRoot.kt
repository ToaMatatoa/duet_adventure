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
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value

    AllPosesScreen(
        state = state,
        searchQuery = searchQuery,
        onPoseClick = onPoseClick,
        onFavouriteClick = viewModel::toggleFavourite,
        onSearchQueryChange = viewModel::updateSearchPoseQuery,
        onCategorySelect = viewModel::selectCategory,
        modifier = modifier,
    )
}
