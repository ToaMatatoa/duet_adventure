package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository

class ToggleFavouriteUseCase(
    private val repository: PoseRepository,
) {
    suspend operator fun invoke(id: Int, isFavorite: Boolean) {
        repository.updateFavorite(id, isFavorite)
    }
}
