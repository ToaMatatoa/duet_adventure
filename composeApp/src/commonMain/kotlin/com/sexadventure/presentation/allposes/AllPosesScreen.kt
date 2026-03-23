package com.sexadventure.presentation.allposes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.listitem.ListItem
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.model.displayName
import com.sexadventure.mapper.resolveImage

@Composable
fun AllPosesScreen(
    state: AllPosesState,
    onPoseClick: (Int) -> Unit,
    onFavouriteClick: (id: Int, currentFavourite: Boolean) -> Unit,
    onCategorySelect: (PoseCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showFilterMenu by remember { mutableStateOf(value = false) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            title = Strings.AllPoses.SCREEN_TITLE,
            showBackButton = false,
            showActions = true,
            onShowFilterClick = { showFilterMenu = true },
            actionContent = {
                DropdownMenu(
                    expanded = showFilterMenu,
                    onDismissRequest = { showFilterMenu = false },
                    offset = DpOffset(x = -(16).dp, y = 8.dp),
                    modifier = Modifier.fillMaxWidth(fraction = 0.3f)
                ) {
                    PoseCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = category.displayName(),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = if (category == state.selectedCategory) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    },
                                )
                            },
                            onClick = {
                                onCategorySelect(category)
                                showFilterMenu = false
                            },
                        )
                    }
                }
            },
        )

        when {
            state.isLoading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            state.poses.isEmpty() -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = Strings.AllPoses.EMPTY_STATE,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
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
                            onFavouriteClick = { onFavouriteClick(pose.id, pose.isFavorite) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllPosesScreenPreview() {
    AllPosesScreen(
        state = AllPosesState(
            poses = listOf(
                PoseData(
                    id = 1,
                    name = "Pose 1",
                    description = "Description for Pose 1",
                    category = "Category A",
                    difficulty = 2,
                    personalScore = 4,
                    imageUrl = "sixty_nine",
                    isFavorite = true,
                ),
                PoseData(
                    id = 2,
                    name = "Pose 2",
                    description = "Description for Pose 2",
                    category = "Category B",
                    difficulty = 3,
                    personalScore = 5,
                    isFavorite = false,
                ),
                PoseData(
                    id = 3,
                    name = "Pose 3. Simply long name",
                    description = "Very long description for pose 3. Some sexy pose. Lorem ipsum dolor sit amet.",
                    category = "Oral",
                    difficulty = 8,
                    personalScore = 9,
                    isFavorite = true,
                ),
            ),
        ),
        onPoseClick = {},
        onFavouriteClick = { _, _ -> },
        onCategorySelect = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AllPosesScreenEmptyPreview() {
    AllPosesScreen(
        state = AllPosesState(),
        onPoseClick = {},
        onFavouriteClick = { _, _ -> },
        onCategorySelect = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AllPosesScreenLoadingPreview() {
    AllPosesScreen(
        state = AllPosesState(isLoading = true),
        onPoseClick = {},
        onFavouriteClick = { _, _ -> },
        onCategorySelect = {},
    )
}
