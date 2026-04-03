package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class UpdateBestPlacesToUseUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(
        id: Int,
        bestPlacesToUse: String,
    ) {
        singlePoseRepository.updateBestPlacesToUse(id, bestPlacesToUse)
    }
}

