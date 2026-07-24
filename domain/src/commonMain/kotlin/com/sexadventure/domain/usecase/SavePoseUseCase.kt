package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository
import com.sexadventure.domain.mapper.toPoseEntity
import com.sexadventure.domain.model.PoseData

class SavePoseUseCase(
    private val poseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(poseData: PoseData) =
        poseRepository.upsertPose(
            pose = poseData.toPoseEntity(),
        )
}
