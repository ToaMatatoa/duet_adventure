package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.STOP_TIMEOUT_MILLIS
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetPosesByCategoryUseCase
import com.sexadventure.domain.usecase.SeedPosesUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AllPosesViewModel(
    private val getPosesByCategoryUseCase: GetPosesByCategoryUseCase,
    private val seedPosesUseCase: SeedPosesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : ViewModel() {
    private val selectedCategory = MutableStateFlow(value = PoseCategory.ALL)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<AllPosesState> =
        selectedCategory
            .flatMapLatest { category ->
                getPosesByCategoryUseCase(category)
                    .map { poses ->
                        AllPosesState(
                            poses = poses,
                            selectedCategory = category,
                            isLoading = false,
                        )
                    }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MILLIS),
                initialValue = AllPosesState(isLoading = true),
            )

    init {
        viewModelScope.launch {
            seedPosesUseCase()
        }
    }

    fun selectCategory(category: PoseCategory) {
        selectedCategory.value = category
    }

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
data class AllPosesState(
    val poses: List<PoseData> = emptyList(),
    val selectedCategory: PoseCategory = PoseCategory.ALL,
    val isLoading: Boolean = false,
)
