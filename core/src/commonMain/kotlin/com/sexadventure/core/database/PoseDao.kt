package com.sexadventure.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PoseDao {
    // ── Insert / Update ─────────────────────────────────────────────

    /** Insert a list of poses, skip any that already exist (by name) */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIgnore(poses: List<PoseEntity>)

    /** Insert or update a single pose by primary key */
    @Upsert
    suspend fun upsertPose(pose: PoseEntity)

    // ── Read ────────────────────────────────────────────────────────

    /** Quick check: how many poses are in the DB */
    @Query("SELECT COUNT(*) FROM pose")
    fun getPosesCount(): Flow<Int>

    /** Quick check: how many predefined poses are in the DB */
    @Query("SELECT COUNT(*) FROM pose WHERE isUserCreated = 0")
    suspend fun getPredefinedCount(): Int

    @Query("SELECT * FROM pose ORDER BY id")
    fun getAllPoses(): Flow<List<PoseEntity>>

    /** Poses whose category column contains the given substring (e.g. "Classic") */
    @Query("SELECT * FROM pose WHERE category LIKE '%' || :category || '%' ORDER BY id")
    fun getPosesByCategory(category: String): Flow<List<PoseEntity>>

    @Query("SELECT * FROM pose WHERE id = :id")
    suspend fun getPoseById(id: Int): PoseEntity?

    /** Only predefined (immutable) poses */
    @Query("SELECT * FROM pose WHERE isUserCreated = 0 ORDER BY id")
    suspend fun getPredefinedPoses(): List<PoseEntity>

    /** Only user-created (mutable) poses */
    @Query("SELECT * FROM pose WHERE isUserCreated = 1 ORDER BY id")
    suspend fun getUserCreatedPoses(): List<PoseEntity>

    // ── Safe partial updates for predefined poses ───────────────────

    /** Toggle favorite – allowed even on predefined poses */
    @Query("UPDATE pose SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    )

    /** Update personal score – allowed even on predefined poses */
    @Query("UPDATE pose SET personalScore = :score WHERE id = :id")
    suspend fun updatePersonalScore(
        id: Int,
        score: Int,
    )

    // ── Delete ──────────────────────────────────────────────────────

    @Query("DELETE FROM pose WHERE id = :id")
    suspend fun deletePose(id: Int)
}
