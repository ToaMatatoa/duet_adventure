package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class DeletePoseUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(id: Int) = singlePoseRepository.deletePose(id = id)
}
