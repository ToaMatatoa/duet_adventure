package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetAllPosesUseCase
import com.sexadventure.domain.usecase.SeedPosesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AllPosesViewModel(
    getAllPosesUseCase: GetAllPosesUseCase,
    private val seedPosesUseCase: SeedPosesUseCase,
) : ViewModel() {
    val state: StateFlow<AllPosesState> =
        getAllPosesUseCase()
            .map { poses ->
                AllPosesState(
                    poses = poses,
                    isLoading = false,
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
                initialValue = AllPosesState(isLoading = true),
            )

    init {
        viewModelScope.launch {
            seedPosesUseCase()
        }
    }
}

@Immutable
data class AllPosesState(
    val poses: List<PoseData> = emptyList(),
    val isLoading: Boolean = false,
)
