package com.sexadventure.designsystem.listitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.theme.FlameColor
import com.sexadventure.designsystem.theme.GoldenColor
import compose.icons.TablerIcons
import compose.icons.tablericons.Flame
import compose.icons.tablericons.Heart
import compose.icons.tablericons.Photo
import compose.icons.tablericons.Star

/**
 * @param image         A [Painter] for the pose image. `null` → placeholder icon.
 * @param title         Pose name — card headline.
 * @param description   Optional secondary text.
 * @param category      Comma-separated categories (e.g. "Classic, Oral").
 * @param difficulty    1..10 difficulty rating.
 * @param personalScore 1..10 user score (0 = not rated yet).
 * @param isFavourite   Whether the heart is filled.
 * @param onClick       Called when the whole card is tapped.
 */
@Composable
fun ListItem(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isUserCreated: Boolean = false,
    image: Painter? = null,
    description: String? = "",
    category: String = "",
    difficulty: Int = 0,
    personalScore: Int = 0,
    isFavourite: Boolean = false,
    onFavouriteClick: () -> Unit = {},
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(
            width = if (isUserCreated) 1.dp else 0.dp,
            color = if (isUserCreated) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(size = 12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(all = 12.dp),
        ) {
            val imageModifier = Modifier
                .size(72.dp)
                .clip(shape = RoundedCornerShape(size = 12.dp))

            if (image != null) {
                Image(
                    painter = image,
                    contentDescription = Strings.Common.POSE_IMAGE,
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                )
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = imageModifier.background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(size = 12.dp),
                    ),
                ) {
                    Icon(
                        imageVector = TablerIcons.Photo,
                        contentDescription = Strings.Common.POSE_NO_IMAGE,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = if (!description.isNullOrEmpty()) {
                        description
                    } else {
                        Strings.Common.NO_POSE_DESCRIPTION
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = if (!description.isNullOrEmpty()) 0.7f else 0.4f,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(8.dp))

                StatsRow(
                    difficulty = difficulty,
                    personalScore = personalScore,
                )

                Spacer(modifier = Modifier.height(2.dp))

                BottomRow(
                    category = category,
                    isFavourite = isFavourite,
                    onFavouriteClick = onFavouriteClick,
                )
            }
        }
    }
}

@Composable
private fun StatsRow(
    difficulty: Int,
    personalScore: Int,
    modifier: Modifier = Modifier,
) {
    val mutedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = TablerIcons.Flame,
            contentDescription = Strings.Common.POSE_DIFFICULTY,
            tint = if (difficulty > 0) FlameColor else mutedColor,
            modifier = Modifier.size(16.dp),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = if (difficulty > 0) "$difficulty/10" else "–/10",
            style = MaterialTheme.typography.labelSmall,
            color = if (difficulty > 0) {
                MaterialTheme.colorScheme.onSurface
            } else {
                mutedColor
            },
        )

        Text(
            text = "  ·  ",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        )

        Icon(
            imageVector = TablerIcons.Star,
            contentDescription = Strings.Common.POSE_PERSONAL_SCORE,
            tint = if (personalScore > 0) GoldenColor else mutedColor,
            modifier = Modifier.size(16.dp),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = if (personalScore > 0) "$personalScore/10" else "–/10",
            style = MaterialTheme.typography.labelSmall,
            color = if (personalScore > 0) {
                MaterialTheme.colorScheme.onSurface
            } else {
                mutedColor
            },
        )
    }
}

@Composable
private fun BottomRow(
    category: String,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false),
        )

        Icon(
            imageVector = TablerIcons.Heart,
            contentDescription = Strings.Common.FAVOURITE_POSE,
            tint = if (isFavourite) FlameColor else MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false, radius = 16.dp),
                    onClick = onFavouriteClick,
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ListItemFullPreview() {
    ListItem(
        image = null,
        title = "Missionary",
        description = "A classic intimate position.",
        category = "Classic",
        difficulty = 3,
        personalScore = 8,
        isFavourite = true,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ListItemMinimalPreview() {
    ListItem(
        image = null,
        title = "Custom Pose With A Very Long Name That Truncates",
        description = null,
        category = "Oral",
        difficulty = 0,
        personalScore = 0,
        isFavourite = false,
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ListItemPartialPreview() {
    ListItem(
        image = null,
        title = "Reverse Cowgirl",
        description = "Advanced position requiring flexibility.",
        category = "Classic, Anal",
        difficulty = 7,
        personalScore = 0,
        isFavourite = false,
        onClick = {},
    )
}
