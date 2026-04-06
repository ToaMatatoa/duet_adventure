package com.sexadventure.presentation.detailpose

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.usecase.DeletePoseUseCase
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.ToggleFavouriteUseCase
import com.sexadventure.domain.usecase.UpdateDifficultyUseCase
import com.sexadventure.domain.usecase.UpdatePersonalScoreUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailPoseViewModel(
    private val poseId: Int,
    private val getPoseByIdUseCase: GetPoseByIdUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val updatePersonalScoreUseCase: UpdatePersonalScoreUseCase,
    private val updateDifficultyUseCase: UpdateDifficultyUseCase,
    private val deletePoseUseCase: DeletePoseUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(value = DetailPoseState())
    val state: StateFlow<DetailPoseState> = _state.asStateFlow()

    private val _effects = Channel<DetailPoseEffects>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        loadPose()
    }

    private fun loadPose() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val pose = getPoseByIdUseCase(poseId)
            _state.update { it.copy(pose = pose, isLoading = false) }
        }
    }

    fun deletePose() {
        val pose = _state.value.pose ?: return
        viewModelScope.launch {
            deletePoseUseCase.invoke(pose.id)
            _effects.send(DetailPoseEffects.ShowMessage)
            _effects.send(DetailPoseEffects.BackToHome)
        }
    }

    fun toggleFavourite() {
        val pose = _state.value.pose ?: return
        val newFavourite = !pose.isFavorite
        viewModelScope.launch {
            toggleFavouriteUseCase(pose.id, isFavorite = newFavourite)
            _state.update {
                it.copy(pose = pose.copy(isFavorite = newFavourite))
            }
        }
    }

    fun updateScore(score: Int) {
        val pose = _state.value.pose ?: return
        viewModelScope.launch {
            updatePersonalScoreUseCase(pose.id, score)
            _state.update {
                it.copy(pose = pose.copy(personalScore = score))
            }
        }
    }

    fun updateDifficulty(difficulty: Int) {
        val pose = _state.value.pose ?: return
        if (!pose.isUserCreated) return
        viewModelScope.launch {
            updateDifficultyUseCase(pose.id, difficulty)
            _state.update {
                it.copy(pose = pose.copy(difficulty = difficulty))
            }
        }
    }
}

@Immutable
data class DetailPoseState(
    val pose: PoseData? = null,
    val isLoading: Boolean = false,
)
