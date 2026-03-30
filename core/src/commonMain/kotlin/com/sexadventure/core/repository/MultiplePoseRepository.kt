package com.sexadventure.core.repository

import com.sexadventure.core.database.entity.PoseEntity
import com.sexadventure.core.datasource.MutablePoseDataSource
import com.sexadventure.core.datasource.PersistentPoseDataSource
import kotlinx.coroutines.flow.Flow

interface MultiplePoseRepository {
    /** Get all possible poses from db */
    fun getAllPoses(): Flow<List<PoseEntity>>

    /** Get poses by category (classic, anal, oral) from db */
    fun getPosesByCategory(category: String): Flow<List<PoseEntity>>

    /** Get only poses they were predefined from db */
    suspend fun getPredefinedPoses(): List<PoseEntity>

    /** Get only poses they were created by user from db */
    suspend fun getUserCreatedPoses(): List<PoseEntity>

    /** Upload all predefined poses to db */
    suspend fun insertAllIgnore(poses: List<PoseEntity>)

    /** Search poses by name */
    fun searchPosesByName(query: String): Flow<List<PoseEntity>>
}

class MultiplePoseRepositoryImpl(
    private val persistentPoseDataSource: PersistentPoseDataSource,
    private val mutablePoseDataSource: MutablePoseDataSource,
) : MultiplePoseRepository {
    /** Persistent methods */
    override fun getAllPoses(): Flow<List<PoseEntity>> = persistentPoseDataSource.getAllPoses()

    override fun getPosesByCategory(category: String): Flow<List<PoseEntity>> = persistentPoseDataSource.getPosesByCategory(category)

    override suspend fun getPredefinedPoses(): List<PoseEntity> = persistentPoseDataSource.getPredefinedPoses()

    override suspend fun getUserCreatedPoses(): List<PoseEntity> = persistentPoseDataSource.getUserCreatedPoses()

    override fun searchPosesByName(query: String): Flow<List<PoseEntity>> = persistentPoseDataSource.searchPosesByName(query = query)

    /** Mutable methods */

    override suspend fun insertAllIgnore(poses: List<PoseEntity>) = mutablePoseDataSource.insertAllIgnore(poses)
}
