package com.sexadventure.designsystem.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.designsystem.strings.Strings
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowLeft

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        navigationIcon = {
            AnimatedVisibility(visible = showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = TablerIcons.ArrowLeft,
                        contentDescription = Strings.TopBar.BACK,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        },
        windowInsets = WindowInsets(top = 16.dp),
        colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
            ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        title = Strings.AllPoses.SCREEN_TITLE,
        showBackButton = true,
    )
}
