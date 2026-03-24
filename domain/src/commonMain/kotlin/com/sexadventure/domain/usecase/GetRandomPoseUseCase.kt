package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseData

class GetRandomPoseUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(): PoseData? = singlePoseRepository.getRandomPose()?.toPoseData()
}
