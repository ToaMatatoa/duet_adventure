package com.sexadventure.presentation.allposes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.listitem.ListItem
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.domain.model.PoseData

@Composable
fun AllPosesScreen(
    state: AllPosesState,
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
            title = Strings.AllPoses.SCREEN_TITLE,
            showBackButton = false,
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp),
        ) {
            items(items = state.poses, key = { it.id }) { pose ->
                ListItem(
                    image = null,
                    title = pose.name,
                    description = pose.description,
                    difficulty = pose.difficulty,
                    personalScore = pose.personalScore,
                    category = pose.category,
                    isFavourite = pose.isFavorite,
                    onClick = {},
                )
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
                            imageUrl = "",
                            category = "Category A",
                            difficulty = 2,
                            personalScore = 4,
                            isFavorite = true,
                            isUserCreated = false,
                        ),
                        PoseData(
                            id = 2,
                            name = "Pose 2",
                            description = "Description for Pose 2",
                            imageUrl = "",
                            category = "Category B",
                            difficulty = 3,
                            personalScore = 5,
                            isFavorite = false,
                            isUserCreated = true,
                        ),
                        PoseData(
                            id = 3,
                            name = "Pose 3. Simply long description as pose duration hihi",
                            description = "Very long description for pose 3. Some sexy pose. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            imageUrl = "",
                            category = "Oral",
                            difficulty = 8,
                            personalScore = 9,
                            isFavorite = true,
                            isUserCreated = true,
                        )
                    ),
            ),
    )
}
