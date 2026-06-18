package com.sexadventure.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sexadventure.logic.ObserveAsEvents
import com.sexadventure.presentation.addpose.AddPoseRoot
import com.sexadventure.presentation.allposes.AllPosesRoot
import com.sexadventure.presentation.detailpose.DetailPoseRoot
import com.sexadventure.presentation.favouriteposes.FavouritePosesRoot
import com.sexadventure.presentation.profile.ProfileRoot
import com.sexadventure.snackbar.SnackbarController
import kotlinx.coroutines.launch

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navigationState =
        rememberNavigationState(
            startRoute = Route.AllPoses,
            topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys.toSet<NavKey>(),
        )
    val navigator = remember { Navigator(navigationState) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(
        flow = SnackbarController.events,
        snackbarHostState,
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = event.message,
                duration = SnackbarDuration.Short,
            )
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (navigationState.topLevelRoute in TOP_LEVEL_DESTINATIONS && navigationState.isAtTopLevel) {
                SexAdventureNavigationBar(
                    selectedKey = navigationState.topLevelRoute,
                    onSelectKey = {
                        navigator.navigate(route = it)
                    },
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.navigationBarsPadding(),
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
                                onOpenAddPoseScreen = {
                                    navigator.navigate(route = Route.AddPose())
                                },
                            )
                        }

                        entry<Route.AddPose> { route ->
                            AddPoseRoot(
                                poseId = route.id,
                                onBackClick = navigator::goBack,
                                onNavigateToAllPoses = navigator::popToTopLevel,
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
                                onOpenAddPoseScreen = {
                                    navigator.navigate(route = Route.AddPose(route.id))
                                },
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
