package com.sexadventure.presentation.favouriteposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.STOP_TIMEOUT_MILLIS
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetFavouritePosesUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import com.sexadventure.domain.usecase.imagestorage.GetImageUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouritePosesViewModel(
    getFavouritePosesUseCase: GetFavouritePosesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getImageUseCase: GetImageUseCase,
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
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MILLIS),
                initialValue = FavouritePosesState(isLoading = true),
            )

    suspend fun loadImage(imageName: String): ByteArray? = getImageUseCase(imageName)

    fun toggleFavourite(
        id: Int,
        currentFavourite: Boolean,
    ) {
        viewModelScope.launch {
            toggleFavouriteUseCase(id = id, isFavorite = !currentFavourite)
        }
    }
}

@Immutable
data class FavouritePosesState(
    val poses: List<PoseData> = emptyList(),
    val isLoading: Boolean = false,
)
