package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.SinglePoseRepository

class UpdateUserCommentsUseCase(
    private val singlePoseRepository: SinglePoseRepository,
) {
    suspend operator fun invoke(
        id: Int,
        userComments: String,
    ) {
        singlePoseRepository.updateUserComments(id, userComments)
    }
}

