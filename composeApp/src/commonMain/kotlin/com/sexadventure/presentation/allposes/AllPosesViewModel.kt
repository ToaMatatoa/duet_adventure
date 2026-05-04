package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.STOP_TIMEOUT_MILLIS
import com.sexadventure.domain.provider.PredefinedPosesProvider
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetPosesByCategoryUseCase
import com.sexadventure.domain.usecase.SearchPosesByNameUseCase
import com.sexadventure.domain.usecase.SeedPosesUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import com.sexadventure.domain.usecase.imagestorage.GetImageUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllPosesViewModel(
    private val posesProvider: PredefinedPosesProvider,
    private val seedPosesUseCase: SeedPosesUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val searchPosesByNameUseCase: SearchPosesByNameUseCase,
    private val getPosesByCategoryUseCase: GetPosesByCategoryUseCase,
    private val getImageUseCase: GetImageUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(value = AllPosesState())
    val searchQuery: StateFlow<String> =
        _state
            .map { it.searchQuery }
            .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<AllPosesState> =
        _state
            .flatMapLatest { currentState ->
                val query = currentState.searchQuery
                val category = currentState.selectedCategory

                if (query.isNotBlank()) {
                    searchPosesByNameUseCase(query).map { poses ->
                        currentState.copy(
                            poses = poses,
                            selectedCategory = PoseCategory.ALL,
                            isLoading = false,
                        )
                    }
                } else {
                    getPosesByCategoryUseCase(category).map { poses ->
                        currentState.copy(
                            poses = poses,
                            isLoading = false,
                        )
                    }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MILLIS),
                initialValue = AllPosesState(isLoading = true),
            )

    init {
        viewModelScope.launch {
            val poses = posesProvider.getPoses()
            seedPosesUseCase.invoke(poses = poses)
        }
    }

    suspend fun loadImage(imageName: String): ByteArray? = getImageUseCase(imageName)

    fun selectCategory(category: PoseCategory) {
        _state.update { it.copy(searchQuery = "", selectedCategory = category) }
    }

    fun updateSearchPoseQuery(query: String) {
        _state.update {
            it.copy(
                searchQuery = query,
                selectedCategory = if (query.isNotBlank()) PoseCategory.ALL else it.selectedCategory,
            )
        }
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
    val searchQuery: String = "",
    val selectedCategory: PoseCategory = PoseCategory.ALL,
    val poses: List<PoseData> = emptyList(),
    val isLoading: Boolean = false,
)
