package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.MultiplePoseRepository
import com.sexadventure.core.repository.PoseAmountRepository
import com.sexadventure.domain.mapper.toPoseEntity
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.provider.PredefinedPosesProvider

/**
 * Seeds predefined poses into the database on first launch.
 * Reads the pose catalogue from [PredefinedPosesProvider] (backed by JSON)
 * and uses insertAllIgnore so existing poses are never overwritten.
 */
class SeedPosesUseCase(
    private val multiplePoseRepository: MultiplePoseRepository,
    private val poseAmountRepository: PoseAmountRepository,
) {
    suspend operator fun invoke(poses: List<PoseData>) {
        if (poseAmountRepository.getPredefinedAmount() > 0) return

        val poses = poses.map { it.toPoseEntity() }
        multiplePoseRepository.insertAllIgnore(poses)
    }
}
