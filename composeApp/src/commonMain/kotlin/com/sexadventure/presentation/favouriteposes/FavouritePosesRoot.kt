package com.sexadventure.presentation.favouriteposes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavouritePosesRoot(
    onPoseClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<FavouritePosesViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    FavouritePosesScreen(
        state = state,
        onPoseClick = onPoseClick,
        onFavouriteClick = viewModel::toggleFavourite,
        modifier = modifier,
    )
}
