package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class ToggleFavouriteUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(
        id: Int,
        isFavorite: Boolean,
    ) {
        singlePoseRepository.updateFavorite(id, isFavorite)
    }
}
