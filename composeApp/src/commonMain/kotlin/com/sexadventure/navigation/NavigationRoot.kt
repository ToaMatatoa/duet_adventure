package com.sexadventure.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sexadventure.presentation.allposes.AllPosesRoot
import com.sexadventure.presentation.detailpose.DetailPoseRoot
import com.sexadventure.presentation.favouriteposes.FavouritePosesRoot
import com.sexadventure.presentation.profile.ProfileRoot

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navigationState =
        rememberNavigationState(
            startRoute = Route.AllPoses,
            topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys.toSet<NavKey>(),
        )
    val navigator = remember { Navigator(navigationState) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            SexAdventureNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(route = it)
                },
            )
        },
    ) { innerPadding ->
        NavDisplay(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues = innerPadding),
            onBack = navigator::goBack,
            entries =
                navigationState.toEntries(
                    entryProvider {
                        entry<Route.AllPoses> {
                            AllPosesRoot(
                                onPoseClick = { poseId ->
                                    navigator.navigate(route = Route.PoseDetails(id = poseId))
                                },
                            )
                        }

                        entry<Route.FavouritePoses> {
                            FavouritePosesRoot(
                                onPoseClick = { poseId ->
                                    navigator.navigate(route = Route.PoseDetails(id = poseId))
                                },
                            )
                        }

                        entry<Route.PoseDetails> { route ->
                            DetailPoseRoot(
                                poseId = route.id,
                                onBackClick = navigator::goBack,
                            )
                        }

                        entry<Route.Profile> {
                            ProfileRoot(
                                onOpenPoseDetails = { poseId ->
                                    navigator.navigate(route = Route.PoseDetails(id = poseId))
                                },
                            )
                        }
                    },
                ),
        )
    }
}
