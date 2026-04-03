package com.sexadventure.core.repository

import com.sexadventure.core.database.entity.PoseEntity
import com.sexadventure.core.database.entity.PoseOfTheDayEntity
import com.sexadventure.core.datasource.MutablePoseDataSource
import com.sexadventure.core.datasource.PersistentPoseDataSource

interface SinglePoseRepository {
    /** Get certain pose by ID from db */
    suspend fun getPoseById(id: Int): PoseEntity?

    /** Get random pose from db */
    suspend fun getRandomPose(): PoseEntity?

    /** Create new pose or update existing in db */
    suspend fun upsertPose(pose: PoseEntity)

    /** Change "isFavourite" status of the pose in db */
    suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    )

    /** Change personal user score for the pose in db */
    suspend fun updatePersonalScore(
        id: Int,
        score: Int,
    )

    /** Update user comments for the pose in db */
    suspend fun updateUserComments(
        id: Int,
        userComments: String,
    )

    /** Update best places to use for the pose in db */
    suspend fun updateBestPlacesToUse(
        id: Int,
        bestPlacesToUse: String,
    )

    /** Get pose of the day (its ID and date) to get full data
     * about this pose from main pose table */
    suspend fun getNecessaryInfoAboutPoseOfTheDay(): PoseOfTheDayEntity?

    /** Save pose of the day (its ID and date) to keep
     necessary information to get full data of the pose then */
    suspend fun saveNecessaryInfoAboutPoseOfTheDay(entity: PoseOfTheDayEntity)

    /** Delete certain pose in db (only created by user) */
    suspend fun deletePose(id: Int)
}

class SinglePoseRepositoryImpl(
    private val persistentPoseDataSource: PersistentPoseDataSource,
    private val mutablePoseDataSource: MutablePoseDataSource,
) : SinglePoseRepository {
    override suspend fun getPoseById(id: Int): PoseEntity? = persistentPoseDataSource.getPoseById(id)

    override suspend fun getRandomPose(): PoseEntity? = persistentPoseDataSource.getRandomPose()

    override suspend fun upsertPose(pose: PoseEntity) = mutablePoseDataSource.upsertPose(pose)

    override suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    ) = mutablePoseDataSource.updateFavorite(id, isFavorite)

    override suspend fun updatePersonalScore(
        id: Int,
        score: Int,
    ) = mutablePoseDataSource.updatePersonalScore(id, score)

    override suspend fun updateUserComments(
        id: Int,
        userComments: String,
    ) = mutablePoseDataSource.updateUserComments(id, userComments)

    override suspend fun updateBestPlacesToUse(
        id: Int,
        bestPlacesToUse: String,
    ) = mutablePoseDataSource.updateBestPlacesToUse(id, bestPlacesToUse)

    override suspend fun getNecessaryInfoAboutPoseOfTheDay(): PoseOfTheDayEntity? =
        persistentPoseDataSource.getNecessaryInfoAboutPoseOfTheDay()

    override suspend fun saveNecessaryInfoAboutPoseOfTheDay(entity: PoseOfTheDayEntity) =
        mutablePoseDataSource.saveNecessaryInfoAboutPoseOfTheDay(entity)

    override suspend fun deletePose(id: Int) = mutablePoseDataSource.deletePose(id)
}
