package com.sexadventure.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TextButton(
                onClick = onGetRandomPose,
                modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 56.dp)
                        .weight(weight = 1f)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium,
                        ),
            ) {
                Text(
                    text = Strings.Profile.GET_RANDOM_POSE,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            AnimatedVisibility(visible = state.pose != null) {
                Box(
                    modifier = Modifier
                        .size(size = 56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium,
                        ).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(bounded = false, radius = 32.dp),
                            onClick = onRemovePose,
                        ),
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

        if (state.pose != null) {
            ListItem(
                title = state.pose.name,
                description = state.pose.description,
                onClick = { onOpenPoseDetails(state.pose.id) },
                image = resolveImage(imageName = state.pose.imageUrl),
                category = state.pose.category,
                difficulty = state.pose.difficulty,
                personalScore = state.pose.personalScore,
                isFavourite = state.pose.isFavorite,
                onFavouriteClick = { onFavouriteClick(state.pose.id, state.pose.isFavorite) },
                modifier = Modifier.padding(top = 8.dp),
            )
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
    )
}
