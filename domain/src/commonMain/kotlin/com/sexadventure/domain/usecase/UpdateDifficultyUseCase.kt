package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class UpdateDifficultyUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(
        id: Int,
        difficulty: Int,
    ) {
        singlePoseRepository.updateDifficulty(id, difficulty)
    }
}
