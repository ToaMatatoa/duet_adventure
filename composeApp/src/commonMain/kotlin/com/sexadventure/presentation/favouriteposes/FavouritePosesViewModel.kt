package com.sexadventure.presentation.favouriteposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetFavouritePosesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavouritePosesViewModel(
    getFavouritePosesUseCase: GetFavouritePosesUseCase,
) : ViewModel() {
    val state: StateFlow<FavouritePosesState> =
        getFavouritePosesUseCase()
            .map { poses ->
                FavouritePosesState(
                    poses = poses,
                    isLoading = false,
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = FavouritePosesState(isLoading = true),
            )
}

@Immutable
data class FavouritePosesState(
    val poses: List<PoseData> = emptyList(),
    val isLoading: Boolean = false,
)
