package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseData

class GetRandomPoseUseCase(
    private val repository: PoseRepository,
) {
    suspend operator fun invoke(): PoseData? = repository.getRandomPose()?.toPoseData()
}
