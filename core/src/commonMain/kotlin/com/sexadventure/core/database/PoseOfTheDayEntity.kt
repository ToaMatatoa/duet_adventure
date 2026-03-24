package com.sexadventure.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Single-row table that stores the current "Pose of the Day".
 * Only keeps a reference to the actual pose (by [poseId])
 * and the [date] it was generated ("yyyy-MM-dd").
 */
@Entity(tableName = "pose_of_the_day")
data class PoseOfTheDayEntity(
    @PrimaryKey val id: Int = 1,
    val poseId: Int,
    val date: String,
)
