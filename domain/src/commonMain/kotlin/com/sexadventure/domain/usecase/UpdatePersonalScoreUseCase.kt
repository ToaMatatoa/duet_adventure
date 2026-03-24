package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class UpdatePersonalScoreUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(
        id: Int,
        score: Int,
    ) {
        singlePoseRepository.updatePersonalScore(id, score)
    }
}
