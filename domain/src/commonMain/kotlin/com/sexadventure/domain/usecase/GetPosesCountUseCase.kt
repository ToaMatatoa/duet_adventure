package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import kotlinx.coroutines.flow.Flow

class GetPosesCountUseCase(
    private val repository: PoseRepository,
) {
    operator fun invoke(): Flow<Int> = repository.getPosesCount()
}
