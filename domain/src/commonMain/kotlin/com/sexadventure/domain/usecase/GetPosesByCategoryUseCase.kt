package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.MultiplePoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPosesByCategoryUseCase(
    private val multiplePoseRepository: MultiplePoseRepository,
) {
    operator fun invoke(category: PoseCategory): Flow<List<PoseData>> =
        if (category == PoseCategory.ALL) {
            multiplePoseRepository.getAllPoses()
        } else {
            multiplePoseRepository.getPosesByCategory(category.name.lowercase().replaceFirstChar { it.uppercase() })
        }.map { poses -> poses.map { pose -> pose.toPoseData() } }
}
