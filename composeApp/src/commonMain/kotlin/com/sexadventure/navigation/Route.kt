package com.sexadventure.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object AllPoses : Route

    @Serializable
    data object AddPose : Route

    @Serializable
    data object FavouritePoses : Route

    @Serializable
    data class PoseDetails(
        val id: Int
    ) : Route

    @Serializable
    data object Profile : Route
}
