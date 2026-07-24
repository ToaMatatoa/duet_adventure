package com.sexadventure.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pose",
    indices = [Index(value = ["name"], unique = true)],
)
data class PoseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val category: String = "",
    val difficulty: Int = 0,
    val personalScore: Int = 0,
    val userComments: String = "",
    val bestPlacesToUse: String = "",
    val isFavorite: Boolean = false,
    val isUserCreated: Boolean = false,
)
