package com.sexadventure.core.datasource

import com.sexadventure.core.database.dao.PoseDao
import com.sexadventure.core.database.entity.PoseEntity
import com.sexadventure.core.database.dao.PoseOfTheDayDao
import com.sexadventure.core.database.entity.PoseOfTheDayEntity

interface MutablePoseDataSource {
    suspend fun insertAllIgnore(poses: List<PoseEntity>)

    suspend fun upsertPose(pose: PoseEntity)

    suspend fun saveNecessaryInfoAboutPoseOfTheDay(entity: PoseOfTheDayEntity)

    suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    )

    suspend fun updatePersonalScore(
        id: Int,
        score: Int,
    )

    suspend fun updateUserComments(
        id: Int,
        userComments: String,
    )

    suspend fun updateDifficulty(
        id: Int,
        difficulty: Int,
    )

    suspend fun updateBestPlacesToUse(
        id: Int,
        bestPlacesToUse: String,
    )

    suspend fun deletePose(id: Int)
}

class MutablePoseDataSourceImpl(
    private val poseDao: PoseDao,
    private val poseOfTheDayDao: PoseOfTheDayDao,
) : MutablePoseDataSource {
    override suspend fun insertAllIgnore(poses: List<PoseEntity>) = poseDao.insertAllIgnore(poses)

    override suspend fun upsertPose(pose: PoseEntity) = poseDao.upsertPose(pose)

    override suspend fun saveNecessaryInfoAboutPoseOfTheDay(entity: PoseOfTheDayEntity) =
        poseOfTheDayDao.upsertNecessaryInfoAboutPoseOfTheDay(entity)

    override suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    ) = poseDao.updateFavorite(id, isFavorite)

    override suspend fun updatePersonalScore(
        id: Int,
        score: Int,
    ) = poseDao.updatePersonalScore(id, score)

    override suspend fun updateUserComments(
        id: Int,
        userComments: String,
    ) = poseDao.updateUserComments(id, userComments)

    override suspend fun updateDifficulty(
        id: Int,
        difficulty: Int,
    ) = poseDao.updateDifficulty(id, difficulty)

    override suspend fun updateBestPlacesToUse(
        id: Int,
        bestPlacesToUse: String,
    ) = poseDao.updateBestPlacesToUse(id, bestPlacesToUse)

    override suspend fun deletePose(id: Int) = poseDao.deletePose(id)
}
