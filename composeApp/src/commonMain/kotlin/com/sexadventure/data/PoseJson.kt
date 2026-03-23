package com.sexadventure.data

import kotlinx.serialization.Serializable

/**
 * Lightweight DTO that mirrors the JSON structure in
 * `composeResources/files/predefined_poses.json`.
 */
@Serializable
data class PoseJson(
    val name: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val difficulty: Int,
)
