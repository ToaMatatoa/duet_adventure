package com.sexadventure.designsystem.strings

/**
 * Centralized string resources for the entire app.
 * Import [Strings] in any module that depends on :designsystem
 * and reference the constants instead of hardcoding text.
 *
 * Usage:  Text(text = Strings.AllPoses.screenTitle)
 */
object Strings {
    /**
     * App
     */
    const val APP_NAME = "Sex Adventure"

    /**
     * Bottom navigation
     */
    object Nav {
        const val TAB_ALL = "All"

        const val TAB_FAVORITES = "Favorites"

        const val TAB_PROFILE = "Profile"
    }

    /* ──────────────────────────────────────────────
     *  All Poses screen
     * ────────────────────────────────────────────── */
    object AllPoses {
        const val SCREEN_TITLE = "All Poses"
        const val EMPTY_STATE = "No poses yet"
    }

    /* ──────────────────────────────────────────────
     *  Favourite Poses screen
     * ────────────────────────────────────────────── */
    object FavouritePoses {
        const val SCREEN_TITLE = "Favourites"
        const val NO_FAVOURITE_POSES = "No favourite poses yet."
    }

    /* ──────────────────────────────────────────────
     *  Pose Detail screen
     * ────────────────────────────────────────────── */
    object PoseDetail {
        const val SCREEN_TITLE = "Pose Details"
    }

    /* ──────────────────────────────────────────────
     *  Profile screen
     * ────────────────────────────────────────────── */
    object Profile {
        const val SCREEN_TITLE = "Profile"
    }

    /* ──────────────────────────────────────────────
     *  Add / Edit Pose screen
     * ────────────────────────────────────────────── */
    object AddEditPose

    /* ──────────────────────────────────────────────
     *  Common / shared
     * ────────────────────────────────────────────── */
    object Common {
        const val OK = "OK"

        const val CANCEL = "Cancel"

        const val BACK = "Back"

        const val FAVOURITE_POSE = "Favourite pose"

        const val POSE_IMAGE = "Pose image"
        const val POSE_NO_IMAGE = "No image available"

        /** Content-description for the difficulty icon in a card */
        const val POSE_DIFFICULTY = "Pose difficulty"
        const val POSE_PERSONAL_SCORE = "User personal score"

        const val NO_POSE_DESCRIPTION = "There is no description provided for this pose."

        const val PLACEHOLDER = "Screen UI/UX to be implemented"
    }
}
