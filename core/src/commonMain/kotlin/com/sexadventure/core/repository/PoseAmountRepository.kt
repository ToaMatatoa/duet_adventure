package com.sexadventure.core.repository

import com.sexadventure.core.datasource.PersistentPoseDataSource
import kotlinx.coroutines.flow.Flow

interface PoseAmountRepository {
    /** Get total amount of poses from db */
    fun getPosesAmount(): Flow<Int>

    /** Get total amount of predefined poses from db */
    suspend fun getPredefinedAmount(): Int

    /** Get total amount of user poses from db */
    suspend fun getUserPosesAmount(): Int
}

class PoseAmountRepositoryImpl(
    private val persistentPoseDataSource: PersistentPoseDataSource,
) : PoseAmountRepository {
    override fun getPosesAmount(): Flow<Int> = persistentPoseDataSource.getPosesAmount()

    override suspend fun getPredefinedAmount(): Int = persistentPoseDataSource.getPredefinedAmount()

    override suspend fun getUserPosesAmount(): Int = persistentPoseDataSource.getUserPosesAmount()
}
