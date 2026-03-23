package com.sexadventure.presentation.detailpose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.theme.FlameColor
import com.sexadventure.designsystem.theme.GoldenColor
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.domain.model.PoseData
import com.sexadventure.mapper.resolveImage
import compose.icons.TablerIcons
import compose.icons.tablericons.Flame
import compose.icons.tablericons.Heart
import compose.icons.tablericons.Photo
import compose.icons.tablericons.Star
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailPoseScreen(
    state: DetailPoseState,
    onBackClick: () -> Unit,
    onToggleFavourite: () -> Unit,
    onScoreChange: (Int) -> Unit,
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
            title = Strings.PoseDetail.SCREEN_TITLE,
            showBackButton = true,
            onBackClick = onBackClick,
        )

        when {
            state.isLoading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(56.dp),
                    )
                }
            }

            state.pose == null -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = Strings.PoseDetail.POSE_NOT_FOUND,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    )
                }
            }

            else -> {
                PoseDetailContent(
                    pose = state.pose,
                    onToggleFavourite = onToggleFavourite,
                    onScoreChange = onScoreChange,
                )
            }
        }
    }
}

@Composable
private fun PoseDetailContent(
    pose: PoseData,
    onToggleFavourite: () -> Unit,
    onScoreChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(horizontal = 16.dp),
    ) {
        val imageResource = resolveImage(imageName = pose.imageUrl)
        val imageModifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(shape = RoundedCornerShape(size = 16.dp))

        if (imageResource != null) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = Strings.Common.POSE_IMAGE,
                contentScale = ContentScale.Crop,
                modifier = imageModifier,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = imageModifier.background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(size = 16.dp),
                    ),
            ) {
                Icon(
                    imageVector = TablerIcons.Photo,
                    contentDescription = Strings.Common.POSE_NO_IMAGE,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(100.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = pose.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f),
            )

            IconButton(onClick = onToggleFavourite) {
                Icon(
                    imageVector = TablerIcons.Heart,
                    contentDescription = Strings.Common.FAVOURITE_POSE,
                    tint = if (pose.isFavorite) {
                            FlameColor
                        } else {
                            MaterialTheme.colorScheme.outline
                        },
                    modifier = Modifier.size(28.dp),
                )
            }
        }

        if (pose.category.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = pose.category,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = pose.description.ifBlank { Strings.Common.NO_POSE_DESCRIPTION },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = if (pose.description.isNotBlank()) 0.7f else 0.4f,
                ),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = TablerIcons.Flame,
                contentDescription = Strings.Common.POSE_DIFFICULTY,
                tint = if (pose.difficulty > 0) {
                        FlameColor
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    },
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (pose.difficulty > 0) {
                        "Difficulty: ${pose.difficulty}/10"
                    } else {
                        "Difficulty: –/10"
                    },
                style = MaterialTheme.typography.titleSmall,
                color = if (pose.difficulty > 0) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                           },
                )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = TablerIcons.Star,
                contentDescription = Strings.Common.POSE_PERSONAL_SCORE,
                tint = if (pose.personalScore > 0) {
                    GoldenColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (pose.personalScore > 0) {
                    "Your score: ${pose.personalScore}/10"
                } else {
                    "Your score: –/10"
                },
                style = MaterialTheme.typography.titleSmall,
                color = if (pose.personalScore > 0) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                    },
                )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = pose.personalScore.toFloat(),
            onValueChange = { onScoreChange(it.toInt()) },
            valueRange = 0f..10f,
            steps = 0,
            colors = SliderDefaults.colors(
                    thumbColor = GoldenColor,
                    activeTrackColor = GoldenColor,
                ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPoseScreenPreview() {
    DetailPoseScreen(
        state = DetailPoseState(
                pose = PoseData(
                        id = 1,
                        name = "Missionary",
                        description = "A classic face-to-face position. One partner lies on their back while the other is on top.",
                        imageUrl = "missionary",
                        category = "Classic",
                        difficulty = 5,
                        personalScore = 5,
                        isFavorite = true,
                    ),
            ),
        onBackClick = {},
        onToggleFavourite = {},
        onScoreChange = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailPoseScreenLoadingPreview() {
    DetailPoseScreen(
        state = DetailPoseState(isLoading = true),
        onBackClick = {},
        onToggleFavourite = {},
        onScoreChange = {},
    )
}
