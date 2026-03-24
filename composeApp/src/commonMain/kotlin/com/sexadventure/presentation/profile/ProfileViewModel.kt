package com.sexadventure.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.STOP_TIMEOUT_MILLIS
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.GetPoseOfTheDayUseCase
import com.sexadventure.domain.usecase.GetPosesCountUseCase
import com.sexadventure.domain.usecase.GetRandomPoseUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    getPosesCountUseCase: GetPosesCountUseCase,
    private val getPoseByIdUseCase: GetPoseByIdUseCase,
    private val getRandomPoseUseCase: GetRandomPoseUseCase,
    private val getPoseOfTheDayUseCase: GetPoseOfTheDayUseCase,
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

    fun loadRandomPoseWhenResume(id: Int) {
        viewModelScope.launch {
            val pose = getPoseByIdUseCase(id)
            _state.update { it.copy(randomPose = pose) }
        }
    }

    fun loadPoseOfTheDayWhenResume(id: Int) {
        viewModelScope.launch {
            val pose = getPoseByIdUseCase(id)
            _state.update { it.copy(poseOfTheDay = pose) }
        }
    }

    fun getRandomPose() {
        val count = state.value.totalPoses
        if (count < 0) return
        viewModelScope.launch {
            getRandomPoseUseCase.invoke()
                ?.let { pose ->
                    _state.update { it.copy(randomPose = pose) }
                }
        }
    }

    fun onRemoveRandomPose() {
        _state.update { it.copy(randomPose = null) }
    }

    fun togglePoseOfTheDay() {
        if (_state.value.showPoseOfTheDay) {
            _state.update { it.copy(showPoseOfTheDay = false) }
        } else {
            viewModelScope.launch {
                val pose = _state.value.poseOfTheDay ?: getPoseOfTheDayUseCase()
                _state.update {
                    it.copy(
                        poseOfTheDay = pose,
                        showPoseOfTheDay = true,
                    )
                }
            }
        }
    }

    fun toggleFavourite(
        id: Int,
        currentFavourite: Boolean,
    ) {
        viewModelScope.launch {
            toggleFavouriteUseCase(id = id, isFavorite = !currentFavourite)
            getPoseByIdUseCase(id = id)?.let { refreshed ->
                _state.update {
                    it.copy(
                        randomPose = if (it.randomPose?.id == id) refreshed else it.randomPose,
                        poseOfTheDay = if (it.poseOfTheDay?.id == id) refreshed else it.poseOfTheDay,
                    )
                }
            }
        }
    }
}

data class ProfileState(
    val totalPoses: Int = -1,
    val randomPose: PoseData? = null,
    val poseOfTheDay: PoseData? = null,
    val showPoseOfTheDay: Boolean = false,
)
