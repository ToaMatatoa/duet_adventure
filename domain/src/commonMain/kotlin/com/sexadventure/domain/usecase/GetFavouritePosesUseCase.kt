package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.MultiplePoseRepository
import com.sexadventure.domain.mapper.toPoseData
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavouritePosesUseCase(
    private val multiplePoseRepository: MultiplePoseRepository,
) {
    operator fun invoke(): Flow<List<PoseData>> =
        multiplePoseRepository
            .getAllPoses()
            .map { poses ->
                poses
                    .filter { favouritePose ->
                        favouritePose.isFavorite
                    }.map { pose ->
                        pose.toPoseData()
                    }
            }
}
