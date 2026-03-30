package com.sexadventure.domain.mapper

import com.sexadventure.core.database.entity.PoseEntity
import com.sexadventure.domain.model.PoseData

fun PoseEntity.toPoseData(): PoseData = PoseData(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    category = category,
    difficulty = difficulty,
    personalScore = personalScore,
    isFavorite = isFavorite,
    isUserCreated = isUserCreated,
)

fun PoseData.toPoseEntity(): PoseEntity = PoseEntity(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    category = category,
    difficulty = difficulty,
    personalScore = personalScore,
    isFavorite = isFavorite,
    isUserCreated = isUserCreated,
)
