package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toDomain
import com.sexadventure.domain.model.PoseData

class GetAllPosesUseCase(
    private val repository: PoseRepository,
) {
    suspend operator fun invoke(): List<PoseData> =
        repository.getAllPoses().map { it.toDomain() }
}
