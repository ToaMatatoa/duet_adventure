package com.sexadventure.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.TablerIcons
import compose.icons.tablericons.Heart
import compose.icons.tablericons.List
import compose.icons.tablericons.User
import com.sexadventure.designsystem.strings.Strings

data class BottomNavItem(
    val icon: ImageVector,
    val title: String,
)

val TOP_LEVEL_DESTINATIONS: Map<Route, BottomNavItem> = mapOf(
        Route.AllPoses to
            BottomNavItem(
                icon = TablerIcons.List,
                title = Strings.Nav.TAB_ALL,
            ),

        Route.FavouritePoses to
            BottomNavItem(
                icon = TablerIcons.Heart,
                title = Strings.Nav.TAB_FAVORITES,
            ),

        Route.Profile to
            BottomNavItem(
                icon = TablerIcons.User,
                title = Strings.Nav.TAB_PROFILE,
            ),
    )
