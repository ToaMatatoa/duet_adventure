package com.sexadventure.designsystem.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
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
import compose.icons.tablericons.ArrowsDownUp
import compose.icons.tablericons.ListSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean,
    modifier: Modifier = Modifier,
    showSearchFilter: Boolean = false,
    showCategoryFilter: Boolean = false,
    onBackClick: () -> Unit = {},
    onShowSearchFilterClick: () -> Unit = {},
    onShowCategoryFilterClick: () -> Unit = {},
    categoryFilterContent: @Composable () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            AnimatedVisibility(visible = showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = TablerIcons.ArrowLeft,
                        contentDescription = Strings.Common.BACK,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        actions = {
            AnimatedVisibility(visible = showSearchFilter) {
                IconButton(onClick = onShowSearchFilterClick) {
                    Icon(
                        imageVector = TablerIcons.ListSearch,
                        contentDescription = Strings.Common.BACK,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
            AnimatedVisibility(visible = showCategoryFilter) {
                IconButton(onClick = onShowCategoryFilterClick) {
                    Icon(
                        imageVector = TablerIcons.ArrowsDownUp,
                        contentDescription = Strings.Common.BACK,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp),
                    )
                }
                categoryFilterContent()
            }
        },
        windowInsets = WindowInsets(top = 0.dp),
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
        showSearchFilter = true,
        showCategoryFilter = true,
    )
}
