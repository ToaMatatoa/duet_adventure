package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toDomain
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPosesByCategoryUseCase(
    private val repository: PoseRepository,
) {
    operator fun invoke(category: PoseCategory): Flow<List<PoseData>> =
        if (category == PoseCategory.ALL) {
            repository.getAllPoses()
        } else {
            repository.getPosesByCategory(category.name.lowercase().replaceFirstChar { it.uppercase() })
        }.map { poses -> poses.map { pose -> pose.toDomain() } }
}
