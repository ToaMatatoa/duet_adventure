package com.sexadventure.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.listitem.ListItem
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.mapper.resolveImage
import compose.icons.TablerIcons
import compose.icons.tablericons.X

@Composable
fun ProfileScreen(
    state: ProfileState,
    onGetRandomPose: () -> Unit,
    onTogglePoseOfTheDay: () -> Unit,
    onFavouriteClick: (id: Int, currentFavourite: Boolean) -> Unit,
    onOpenPoseDetails: (Int) -> Unit,
    onRemovePose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        TopBar(
            title = Strings.Profile.SCREEN_TITLE,
            showBackButton = false,
        )

        RandomPoseElement(
            state = state,
            onGetRandomPose = onGetRandomPose,
            onFavouriteClick = onFavouriteClick,
            onOpenPoseDetails = onOpenPoseDetails,
            onRemovePose = onRemovePose,
            modifier = Modifier.padding(top = 16.dp),
        )

        PoseOfTheDayElement(
            state = state,
            onToggle = onTogglePoseOfTheDay,
            onFavouriteClick = onFavouriteClick,
            onOpenPoseDetails = onOpenPoseDetails,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Composable
private fun RandomPoseElement(
    state: ProfileState,
    onGetRandomPose: () -> Unit,
    onFavouriteClick: (id: Int, currentFavourite: Boolean) -> Unit,
    onOpenPoseDetails: (Int) -> Unit,
    onRemovePose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(
                onClick = onGetRandomPose,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 56.dp)
                    .weight(weight = 1f),
            ) {
                Text(
                    text = Strings.Profile.GET_RANDOM_POSE,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            AnimatedVisibility(visible = state.randomPose != null) {
                Box(
                    modifier = Modifier
                        .size(size = 56.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .clickable(onClick = onRemovePose),
                ) {
                    Icon(
                        imageVector = TablerIcons.X,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(size = 32.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }

        if (state.randomPose != null) {
            ListItem(
                title = state.randomPose.name,
                description = state.randomPose.description,
                onClick = { onOpenPoseDetails(state.randomPose.id) },
                image = resolveImage(imageName = state.randomPose.imageUrl),
                category = state.randomPose.category,
                difficulty = state.randomPose.difficulty,
                personalScore = state.randomPose.personalScore,
                isFavourite = state.randomPose.isFavorite,
                onFavouriteClick = { onFavouriteClick(state.randomPose.id, state.randomPose.isFavorite) },
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun PoseOfTheDayElement(
    state: ProfileState,
    onToggle: () -> Unit,
    onFavouriteClick: (id: Int, currentFavourite: Boolean) -> Unit,
    onOpenPoseDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TextButton(
            onClick = onToggle,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp),
        ) {
            Text(
                text = if (state.showPoseOfTheDay) {
                    Strings.Profile.HIDE_POSE_OF_THE_DAY
                } else {
                    Strings.Profile.SHOW_POSE_OF_THE_DAY
                },
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        AnimatedVisibility(visible = state.showPoseOfTheDay && state.poseOfTheDay != null) {
            state.poseOfTheDay?.let { pose ->
                ListItem(
                    title = pose.name,
                    description = pose.description,
                    onClick = { onOpenPoseDetails(pose.id) },
                    image = resolveImage(imageName = pose.imageUrl),
                    category = pose.category,
                    difficulty = pose.difficulty,
                    personalScore = pose.personalScore,
                    isFavourite = pose.isFavorite,
                    onFavouriteClick = { onFavouriteClick(pose.id, pose.isFavorite) },
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        state = ProfileState(),
        onGetRandomPose = {},
        onFavouriteClick = { _, _ -> },
        onOpenPoseDetails = {},
        onRemovePose = {},
        onTogglePoseOfTheDay = {},
    )
}
