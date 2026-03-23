package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toDomain
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllPosesUseCase(
    private val repository: PoseRepository,
) {
    operator fun invoke(): Flow<List<PoseData>> =
        repository
            .getAllPoses()
            .map { poses ->
                poses.map { pose ->
                    pose.toDomain()
                }
            }
}
