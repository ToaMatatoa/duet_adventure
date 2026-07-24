package com.sexadventure.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sexadventure.core.database.entity.PoseOfTheDayEntity

@Dao
interface PoseOfTheDayDao {
    @Query("SELECT * FROM pose_of_the_day WHERE id = 1")
    suspend fun getNecessaryInfoAboutPoseOfTheDay(): PoseOfTheDayEntity?

    @Upsert
    suspend fun upsertNecessaryInfoAboutPoseOfTheDay(entity: PoseOfTheDayEntity)
}