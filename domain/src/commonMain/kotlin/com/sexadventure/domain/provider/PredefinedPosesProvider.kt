package com.sexadventure.domain.provider

import com.sexadventure.domain.model.PoseData

/**
 * Provides the list of predefined poses that ship with the app.
 * Implemented in :composeApp where Compose resources are available.
 */
fun interface PredefinedPosesProvider {
    suspend fun getPoses(): List<PoseData>
}
