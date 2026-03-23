package com.sexadventure.presentation.allposes

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AllPosesViewModel : ViewModel() {
    private val _state: MutableStateFlow<AllPosesState> = MutableStateFlow(value = AllPosesState())
    val state: StateFlow<AllPosesState> = _state.asStateFlow()
}

@Immutable
data class AllPosesState(
    val poses: List<PoseData> = emptyList(),
)
