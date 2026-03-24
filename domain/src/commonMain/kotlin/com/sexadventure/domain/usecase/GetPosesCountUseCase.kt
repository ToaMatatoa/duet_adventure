package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseAmountRepository
import kotlinx.coroutines.flow.Flow

class GetPosesCountUseCase(
    private val poseAmountRepository: PoseAmountRepository,
) {
    operator fun invoke(): Flow<Int> = poseAmountRepository.getPosesAmount()
}
