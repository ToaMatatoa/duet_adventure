package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toEntity
import com.sexadventure.domain.provider.PredefinedPosesProvider

/**
 * Seeds predefined poses into the database on first launch.
 * Reads the pose catalogue from [PredefinedPosesProvider] (backed by JSON)
 * and uses insertAllIgnore so existing poses are never overwritten.
 */
class SeedPosesUseCase(
    private val repository: PoseRepository,
    private val posesProvider: PredefinedPosesProvider,
) {
    suspend operator fun invoke() {
        if (repository.getPredefinedCount() > 0) return

        val poses = posesProvider.getPoses().map { it.toEntity() }
        repository.insertAllIgnore(poses)
    }
}
