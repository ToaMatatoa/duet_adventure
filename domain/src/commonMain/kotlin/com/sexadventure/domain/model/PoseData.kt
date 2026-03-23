package com.sexadventure.domain.model

data class PoseData(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val category: String = "",
    val difficulty: Int = 0,
    val personalScore: Int = 0,
    val isFavorite: Boolean = false,
    val isUserCreated: Boolean = false,
)
