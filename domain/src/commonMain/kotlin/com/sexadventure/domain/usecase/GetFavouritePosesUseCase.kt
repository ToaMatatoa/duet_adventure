package com.sexadventure.domain.usecase

import com.sexadventure.core.repository.PoseRepository
import com.sexadventure.domain.mapper.toDomain
import com.sexadventure.domain.model.PoseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavouritePosesUseCase(
    private val repository: PoseRepository,
) {
    operator fun invoke(): Flow<List<PoseData>> =
        repository
            .getAllPoses()
            .map { poses ->
                poses.filter { favouritePose ->
                        favouritePose.isFavorite
                    }.map { pose ->
                        pose.toDomain()
                    }
            }
}
