package com.sexadventure.presentation.favouriteposes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.listitem.ListItem
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.domain.model.PoseData
import com.sexadventure.mapper.resolveImage

@Composable
fun FavouritePosesScreen(
    state: FavouritePosesState,
    onPoseClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            title = Strings.FavouritePoses.SCREEN_TITLE,
            showBackButton = false,
        )

        when {
            state.isLoading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(60.dp),
                    )
                }
            }

            state.poses.isEmpty() -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = Strings.FavouritePoses.NO_FAVOURITE_POSES,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    )
                }
            }

            else -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp),
                ) {
                    items(items = state.poses, key = { it.id }) { pose ->
                        ListItem(
                            image = resolveImage(imageName = pose.imageUrl),
                            title = pose.name,
                            description = pose.description,
                            difficulty = pose.difficulty,
                            personalScore = pose.personalScore,
                            category = pose.category,
                            isFavourite = pose.isFavorite,
                            onClick = { onPoseClick(pose.id) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavouritePosesScreenPreview() {
    FavouritePosesScreen(
        state = FavouritePosesState(
                poses = listOf(
                        PoseData(
                            id = 1,
                            name = "Missionary",
                            description = "A classic face-to-face position.",
                            category = "Classic",
                            difficulty = 1,
                            personalScore = 7,
                            isFavorite = true,
                        ),
                    ),
            ),
        onPoseClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun FavouritePosesScreenEmptyPreview() {
    FavouritePosesScreen(
        state = FavouritePosesState(
            isLoading = true
        ),
        onPoseClick = {},
    )
}
