package com.sexadventure.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.STOP_TIMEOUT_MILLIS
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.GetPosesCountUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProfileViewModel(
    getPosesCountUseCase: GetPosesCountUseCase,
    private val getPoseByIdUseCase: GetPoseByIdUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(value = ProfileState())
    val state: StateFlow<ProfileState> =
        combine(
            flow = getPosesCountUseCase(),
            flow2 = _state,
        ) { count, currentState ->
            currentState.copy(totalPoses = count)
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = STOP_TIMEOUT_MILLIS),
            initialValue = ProfileState(),
        )

    fun loadPose(id: Int) {
        viewModelScope.launch {
            val pose = getPoseByIdUseCase(id)
            _state.update { it.copy(pose = pose) }
        }
    }

    fun getRandomPose() {
        val count = state.value.totalPoses
        if (count < 0) return
        viewModelScope.launch {
            getPoseByIdUseCase(id = Random.nextInt(from = 0, until = count))
                ?.let { pose ->
                    _state.update { it.copy(pose = pose) }
                }
        }
    }

    fun onRemovePose() {
        _state.update { it.copy(pose = null) }
    }

    fun toggleFavourite(
        id: Int,
        currentFavourite: Boolean,
    ) {
        viewModelScope.launch {
            toggleFavouriteUseCase(id = id, isFavorite = !currentFavourite)
            getPoseByIdUseCase(id = id)
                ?.let { pose ->
                    _state.update { it.copy(pose = pose) }
                }
        }
    }
}

data class ProfileState(
    val totalPoses: Int = -1,
    val pose: PoseData? = null,
)
