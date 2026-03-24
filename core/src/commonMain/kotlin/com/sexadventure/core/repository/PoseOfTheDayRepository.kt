package com.sexadventure.core.repository

import com.sexadventure.core.database.PoseOfTheDayDao
import com.sexadventure.core.database.PoseOfTheDayEntity

interface PoseOfTheDayRepository {
    suspend fun get(): PoseOfTheDayEntity?
    suspend fun save(entity: PoseOfTheDayEntity)
}

class PoseOfTheDayRepositoryImpl(
    private val dao: PoseOfTheDayDao,
) : PoseOfTheDayRepository {

    override suspend fun get(): PoseOfTheDayEntity? = dao.get()

    override suspend fun save(entity: PoseOfTheDayEntity) = dao.upsert(entity)
}
