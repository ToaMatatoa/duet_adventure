package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.MultiplePoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchPosesByNameUseCase(
    private val multiplePoseRepository: MultiplePoseRepository,
) {
    operator fun invoke(query: String): Flow<List<PoseData>> =
        multiplePoseRepository
            .searchPosesByName(query)
            .map { poses ->
                poses.map { pose ->
                    pose.toPoseData()
                }
            }
}
