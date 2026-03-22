package com.sexadventure.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.TablerIcons
import compose.icons.tablericons.Heart
import compose.icons.tablericons.List
import compose.icons.tablericons.User

data class BottomNavItem(
    val icon: ImageVector,
    val title: String,
)

val TOP_LEVEL_DESTINATIONS: Map<Route, BottomNavItem> = mapOf(
        Route.AllPoses to
            BottomNavItem(
                icon = TablerIcons.List,
                title = "All",
            ),

        Route.FavouritePoses to
            BottomNavItem(
                icon = TablerIcons.Heart,
                title = "Favorites",
            ),

        Route.Profile to
            BottomNavItem(
                icon = TablerIcons.User,
                title = "Profile",
            ),
    )
