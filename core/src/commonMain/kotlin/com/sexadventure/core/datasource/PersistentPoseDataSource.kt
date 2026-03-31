package com.sexadventure.core.datasource

import com.sexadventure.core.database.dao.PoseDao
import com.sexadventure.core.database.dao.PoseOfTheDayDao
import com.sexadventure.core.database.entity.PoseEntity
import com.sexadventure.core.database.entity.PoseOfTheDayEntity
import kotlinx.coroutines.flow.Flow

interface PersistentPoseDataSource {
    fun getAllPoses(): Flow<List<PoseEntity>>

    fun getPosesByCategory(category: String): Flow<List<PoseEntity>>

    suspend fun getPoseById(id: Int): PoseEntity?

    suspend fun getRandomPose(): PoseEntity?

    suspend fun getNecessaryInfoAboutPoseOfTheDay(): PoseOfTheDayEntity?

    suspend fun getPredefinedPoses(): List<PoseEntity>

    suspend fun getUserCreatedPoses(): List<PoseEntity>

    fun getPosesAmount(): Flow<Int>

    suspend fun getPredefinedAmount(): Int

    suspend fun getUserPosesAmount(): Int

    fun searchPosesByName(query: String): Flow<List<PoseEntity>>
}

class PersistentPoseDataSourceImpl(
    private val poseDao: PoseDao,
    private val poseOfTheDayDao: PoseOfTheDayDao,
) : PersistentPoseDataSource {
    override fun getAllPoses(): Flow<List<PoseEntity>> = poseDao.getAllPoses()

    override fun getPosesByCategory(category: String): Flow<List<PoseEntity>> = poseDao.getPosesByCategory(category)

    override suspend fun getPoseById(id: Int): PoseEntity? = poseDao.getPoseById(id)

    override suspend fun getRandomPose(): PoseEntity? = poseDao.getRandomPose()

    override suspend fun getNecessaryInfoAboutPoseOfTheDay(): PoseOfTheDayEntity? = poseOfTheDayDao.getNecessaryInfoAboutPoseOfTheDay()

    override suspend fun getPredefinedPoses(): List<PoseEntity> = poseDao.getPredefinedPoses()

    override suspend fun getUserCreatedPoses(): List<PoseEntity> = poseDao.getUserCreatedPoses()

    override fun getPosesAmount(): Flow<Int> = poseDao.getPosesAmount()

    override suspend fun getPredefinedAmount(): Int = poseDao.getPredefinedAmount()

    override suspend fun getUserPosesAmount(): Int = poseDao.getUserPosesAmount()

    override fun searchPosesByName(query: String): Flow<List<PoseEntity>> = poseDao.searchPosesByName(query = query)
}
