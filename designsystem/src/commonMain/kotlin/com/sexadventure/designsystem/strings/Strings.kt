package com.sexadventure.designsystem.strings

/**
 * Centralized string resources for the entire app.
 * Import [Strings] in any module that depends on :designsystem
 * and reference the constants instead of hardcoding text.
 *
 * Usage:  Text(text = Strings.AllPoses.screenTitle)
 */
object Strings {
    /* ──────────────────────────────────────────────
     *  Bottom navigation
     * ────────────────────────────────────────────── */
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
        const val ENTER_POSE_NAME = "Enter pose name"
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
        const val POSE_NOT_FOUND = "Pose not found"
        const val USER_COMMENTS_LABEL = "User comments"
    }

    /* ──────────────────────────────────────────────
     *  Profile screen
     * ────────────────────────────────────────────── */
    object Profile {
        const val SCREEN_TITLE = "Profile"
        const val GET_RANDOM_POSE = "Get random pose"
        const val SHOW_POSE_OF_THE_DAY = "Show pose of the day"
        const val HIDE_POSE_OF_THE_DAY = "Hide pose of the day"
    }

    /* ──────────────────────────────────────────────
     *  Add / Edit Pose screen
     * ────────────────────────────────────────────── */
    object AddEditPose {
        const val SCREEN_TITLE_ADD = "Add Pose"
        const val SCREEN_TITLE_EDIT = "Edit Pose"
        const val POSE_IMAGE = "Pose image"
        const val REMOVE_POSE_IMAGE = "Remove pose image"
        const val CHOOSE_PHOTO_FROM_GALLERY = "Choose photo from gallery"
        const val ONLY_ALLOW_PNG = "Only allows PNG images"
        const val POSE_NAME_LABEL = "Pose name"
        const val POSE_DESCRIPTION_LABEL = "Pose description"
        const val REQUIRED_FIELDS_HINT = "* Name, category, and difficulty are required"
        const val POSE_SAVED = "Pose saved successfully"
    }

    /* ──────────────────────────────────────────────
     *  Common / shared
     * ────────────────────────────────────────────── */
    object Common {
        const val OK = "OK"

        const val SAVE = "Save"

        const val CANCEL = "Cancel"

        const val BACK = "Back"

        const val FAVOURITE_POSE = "Favourite pose"

        const val POSE_IMAGE = "Pose image"
        const val POSE_NO_IMAGE = "No image available"

        const val ADD_NEW_POSE = "Add new pose"

        const val POSE_DIFFICULTY = "Pose difficulty"
        const val POSE_PERSONAL_SCORE = "User personal score"
        const val USER_COMMENTS_LABEL = "User comments"

        const val NO_POSE_DESCRIPTION = "There is no description provided for this pose."

        const val PLACEHOLDER = "Screen UI/UX to be implemented"
    }
}
