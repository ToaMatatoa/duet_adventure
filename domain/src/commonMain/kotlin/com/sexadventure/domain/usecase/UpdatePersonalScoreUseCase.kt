package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository

class UpdatePersonalScoreUseCase(
    private val repository: PoseRepository,
) {
    suspend operator fun invoke(id: Int, score: Int) {
        repository.updatePersonalScore(id, score)
    }
}
