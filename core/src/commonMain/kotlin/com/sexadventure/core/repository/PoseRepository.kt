package com.sexadventure.core.repository

import com.sexadventure.core.database.PoseDao
import com.sexadventure.core.database.PoseEntity
import kotlinx.coroutines.flow.Flow

interface PoseRepository {
    fun getAllPoses(): Flow<List<PoseEntity>>
    fun getPosesByCategory(category: String): Flow<List<PoseEntity>>
    suspend fun getPoseById(id: Int): PoseEntity?
    suspend fun getPredefinedPoses(): List<PoseEntity>
    suspend fun getUserCreatedPoses(): List<PoseEntity>
    fun getPosesCount(): Flow<Int>
    suspend fun getPredefinedCount(): Int
    suspend fun insertAllIgnore(poses: List<PoseEntity>)
    suspend fun upsertPose(pose: PoseEntity)
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)
    suspend fun updatePersonalScore(id: Int, score: Int)
    suspend fun deletePose(id: Int)
    suspend fun getRandomPose(): PoseEntity?
}

class PoseRepositoryImpl(
    private val poseDao: PoseDao,
) : PoseRepository {

    override fun getAllPoses(): Flow<List<PoseEntity>> =
        poseDao.getAllPoses()

    override fun getPosesByCategory(category: String): Flow<List<PoseEntity>> =
        poseDao.getPosesByCategory(category)

    override suspend fun getPoseById(id: Int): PoseEntity? =
        poseDao.getPoseById(id)

    override suspend fun getPredefinedPoses(): List<PoseEntity> =
        poseDao.getPredefinedPoses()

    override suspend fun getUserCreatedPoses(): List<PoseEntity> =
        poseDao.getUserCreatedPoses()

    override fun getPosesCount(): Flow<Int> =
        poseDao.getPosesCount()

    override suspend fun getPredefinedCount(): Int =
        poseDao.getPredefinedCount()

    override suspend fun insertAllIgnore(poses: List<PoseEntity>) =
        poseDao.insertAllIgnore(poses)

    override suspend fun upsertPose(pose: PoseEntity) =
        poseDao.upsertPose(pose)

    override suspend fun updateFavorite(id: Int, isFavorite: Boolean) =
        poseDao.updateFavorite(id, isFavorite)

    override suspend fun updatePersonalScore(id: Int, score: Int) =
        poseDao.updatePersonalScore(id, score)

    override suspend fun deletePose(id: Int) =
        poseDao.deletePose(id)

    override suspend fun getRandomPose(): PoseEntity? =
        poseDao.getRandomPose()
}

