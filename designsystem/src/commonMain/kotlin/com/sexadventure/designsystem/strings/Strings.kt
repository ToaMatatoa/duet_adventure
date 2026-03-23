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
     *  App-level
     * ────────────────────────────────────────────── */

    /** Shown in app title bars, splash, about screen */
    const val APP_NAME = "Sex Adventure"

    /* ──────────────────────────────────────────────
     *  Bottom navigation bar labels
     * ────────────────────────────────────────────── */
    object Nav {
        /** Tab label – list of all poses */
        const val TAB_ALL = "All"

        /** Tab label – favorite poses */
        const val TAB_FAVORITES = "Favorites"

        /** Tab label – user profile */
        const val TAB_PROFILE = "Profile"
    }

    /* ──────────────────────────────────────────────
     *  Top bar / toolbar
     * ────────────────────────────────────────────── */
    object TopBar {
        /** Content-description for the back arrow icon */
        const val BACK = "Back"
    }

    /* ──────────────────────────────────────────────
     *  All Poses screen
     * ────────────────────────────────────────────── */
    object AllPoses {
        /** Screen title shown in the top bar */
        const val SCREEN_TITLE = "All Poses"

        /** Placeholder text while the screen is not yet implemented */
        const val PLACEHOLDER = "All Poses Screen\nTo be implemented"

        /** Shown when the pose list is empty */
        const val EMPTY_STATE = "No poses yet"

        /** Search-bar hint */
        const val SEARCH_HINT = "Search poses…"
    }

    /* ──────────────────────────────────────────────
     *  Favourite Poses screen
     * ────────────────────────────────────────────── */
    object FavouritePoses {
        /** Screen title shown in the top bar */
        const val SCREEN_TITLE = "Favourites"

        /** Placeholder text while the screen is not yet implemented */
        const val PLACEHOLDER = "Favourite Poses Screen\nTo be implemented"

        /** Shown when no poses are favourited */
        const val EMPTY_STATE = "No favourites yet"
    }

    /* ──────────────────────────────────────────────
     *  Pose Detail screen
     * ────────────────────────────────────────────── */
    object PoseDetail {
        /** Screen title (usually replaced with the pose name) */
        const val SCREEN_TITLE = "Pose Details"

        /** Placeholder text while the screen is not yet implemented */
        const val PLACEHOLDER = "Detail Pose Screen\nTo be implemented"

        /** Label next to the difficulty indicator */
        const val DIFFICULTY_LABEL = "Difficulty"

        /** Label next to the personal score slider / stars */
        const val SCORE_LABEL = "Your score"

        /** Label for the category chip */
        const val CATEGORY_LABEL = "Category"

        /** Button text – add / remove from favourites */
        const val ADD_FAVOURITE = "Add to favourites"
        const val REMOVE_FAVOURITE = "Remove from favourites"

        /** Button text – delete a user-created pose */
        const val DELETE_POSE = "Delete pose"

        /** Confirmation dialog title when deleting a pose */
        const val DELETE_CONFIRM_TITLE = "Delete this pose?"

        /** Confirmation dialog body */
        const val DELETE_CONFIRM_BODY = "This action cannot be undone."

        /** Dialog positive / negative buttons */
        const val CONFIRM = "Delete"
        const val CANCEL = "Cancel"
    }

    /* ──────────────────────────────────────────────
     *  Profile screen
     * ────────────────────────────────────────────── */
    object Profile {
        /** Screen title shown in the top bar */
        const val SCREEN_TITLE = "Profile"

        /** Placeholder text while the screen is not yet implemented */
        const val PLACEHOLDER = "Profile Screen\nTo be implemented"

        /** Settings section header */
        const val SETTINGS_HEADER = "Settings"

        /** Dark-mode toggle label */
        const val DARK_MODE = "Dark mode"

        /** About / version row */
        const val ABOUT = "About"
    }

    /* ──────────────────────────────────────────────
     *  Add / Edit Pose screen (future)
     * ────────────────────────────────────────────── */
    object AddEditPose {
        /** Screen title – creating a new pose */
        const val TITLE_ADD = "Add Pose"

        /** Screen title – editing an existing pose */
        const val TITLE_EDIT = "Edit Pose"

        /** Text-field labels */
        const val NAME_LABEL = "Name"
        const val DESCRIPTION_LABEL = "Description"
        const val IMAGE_URL_LABEL = "Image URL"
        const val CATEGORY_LABEL = "Category"
        const val DIFFICULTY_LABEL = "Difficulty"

        /** Save button */
        const val SAVE = "Save"
    }

    /* ──────────────────────────────────────────────
     *  Common / shared
     * ────────────────────────────────────────────── */
    object Common {
        /** Generic OK button */
        const val OK = "OK"

        /** Generic Cancel button */
        const val CANCEL = "Cancel"

        /** Generic error title */
        const val ERROR_TITLE = "Something went wrong"

        /** Generic retry button */
        const val RETRY = "Retry"

        /** Content-description for the favourite (heart) icon – filled */
        const val HEART_FILLED_CD = "Remove from favourites"

        /** Content-description for the favourite (heart) icon – outline */
        const val HEART_OUTLINE_CD = "Add to favourites"

        /** Loading indicator label */
        const val LOADING = "Loading…"
    }
}
