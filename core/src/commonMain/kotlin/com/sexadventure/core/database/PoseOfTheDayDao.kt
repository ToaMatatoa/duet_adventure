package com.sexadventure.core.database
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface PoseOfTheDayDao {
    @Query("SELECT * FROM pose_of_the_day WHERE id = 1")
    suspend fun get(): PoseOfTheDayEntity?
    @Upsert
    suspend fun upsert(entity: PoseOfTheDayEntity)
}
