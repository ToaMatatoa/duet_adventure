package com.sexadventure.domain.mapper

import com.sexadventure.core.database.PoseEntity
import com.sexadventure.domain.model.PoseData

fun PoseEntity.toDomain(): PoseData = PoseData(
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

fun PoseData.toEntity(): PoseEntity = PoseEntity(
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
