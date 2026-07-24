package com.sexadventure.presentation.addpose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sexadventure.POSE_DESCRIPTION_MAX_LENGTH
import com.sexadventure.POSE_NAME_MAX_LENGTH
import com.sexadventure.USER_COMMENTS_MAX_LENGTH
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.designsystem.textfield.ProjectOutlinedTextField
import com.sexadventure.designsystem.theme.FlameColor
import com.sexadventure.designsystem.theme.GoldenColor
import com.sexadventure.designsystem.topbar.TopBar
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.mapper.resolvePainter
import compose.icons.TablerIcons
import compose.icons.tablericons.Star
import compose.icons.tablericons.X
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.domain.extensions.loadPainter
import io.github.ismoy.imagepickerkmp.domain.models.GalleryPhotoResult
import io.github.ismoy.imagepickerkmp.domain.models.PhotoResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP

@Composable
fun AddPoseScreen(
    state: AddPoseState,
    loadImage: suspend (String) -> ByteArray?,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onDifficultyChange: (Int) -> Unit,
    onPersonalScoreChange: (Int) -> Unit,
    onUserCommentsChange: (String) -> Unit,
    onImageSelected: (ByteArray?) -> Unit,
    onImageRemoved: () -> Unit,
    onBackClick: () -> Unit,
    onSavePoseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val commentsFocusRequester = remember { FocusRequester() }

    var showGallery by remember { mutableStateOf(value = false) }
    var selectedImage by remember { mutableStateOf<PhotoResult?>(value = null) }
    // ToDo best place to fuck

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .imePadding()
            .clickable(
                interactionSource = null,
                indication = null,
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
    ) {
        TopBar(
            title = if (state.isEditMode) Strings.AddEditPose.SCREEN_TITLE_EDIT else Strings.AddEditPose.SCREEN_TITLE_ADD,
            showBackButton = true,
            onBackClick = onBackClick,
        )

        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(56.dp),
                )
            }
        } else {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState()),
            ) {
                PosePhoto(
                    selectedImage = selectedImage,
                    existingImageUrl = state.imageUrl,
                    loadImage = loadImage,
                    showGallery = showGallery,
                    onSelectImage = { photos ->
                        val photo = photos.firstOrNull()
                        selectedImage = photo
                        if (photo != null) {
                            onImageSelected(photo.loadBytes())
                        } else {
                            onImageRemoved()
                        }
                    },
                    onShowGalleryChange = { showGallery = it },
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProjectOutlinedTextField(
                    value = state.name,
                    onValueChange = onNameChange,
                    label = Strings.AddEditPose.POSE_NAME_LABEL,
                    valueMaxLength = POSE_NAME_MAX_LENGTH,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = Next,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProjectOutlinedTextField(
                    value = state.description,
                    onValueChange = onDescriptionChange,
                    label = Strings.AddEditPose.POSE_DESCRIPTION_LABEL,
                    valueMaxLength = POSE_DESCRIPTION_MAX_LENGTH,
                    maxLines = 4,
                    keyboardOptions = KeyboardOptions(
                        imeAction = Next,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            commentsFocusRequester.requestFocus()
                        },
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                PoseCategory(
                    isCategoryChosen = { category -> state.categories.contains(category) },
                    onCategoryClick = onCategoryClick,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                PoseDifficultyContent(
                    difficulty = state.difficulty,
                    onDifficultyChange = onDifficultyChange,
                )

                Spacer(modifier = Modifier.height(16.dp))

                UserPersonalScoreContent(
                    personalScore = state.personalScore,
                    onPersonalScoreChange = onPersonalScoreChange,
                )

                Spacer(modifier = Modifier.height(16.dp))

                UserCommentsContent(
                    userComments = state.userComment,
                    onUserCommentsChange = onUserCommentsChange,
                    onDoneButtonClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    },
                    modifier = Modifier.focusRequester(commentsFocusRequester),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = Strings.AddEditPose.REQUIRED_FIELDS_HINT,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            TextButton(
                onClick = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    onSavePoseClick()
                },
                enabled = state.isValid,
                modifier = Modifier
                    .height(height = 56.dp)
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp)
                    .background(
                        color = if (state.isValid) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                        },
                        shape = MaterialTheme.shapes.medium,
                    ),
            ) {
                Text(
                    text = if (state.isEditMode) Strings.Common.UPDATE else Strings.Common.SAVE,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = if (state.isValid) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    },
                )
            }
        }
    }
}

@Composable
private fun ImageBox(
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(height = 280.dp)
            .background(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium,
            ),
    ) {
        content()

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size = 64.dp)
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f))
                .clickable { onRemove() },
        ) {
            Icon(
                imageVector = TablerIcons.X,
                contentDescription = Strings.AddEditPose.REMOVE_POSE_IMAGE,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(size = 32.dp),
            )
        }
    }
}

@Composable
private fun PosePhoto(
    selectedImage: PhotoResult?,
    existingImageUrl: String,
    loadImage: suspend (String) -> ByteArray?,
    showGallery: Boolean,
    onSelectImage: (List<GalleryPhotoResult>) -> Unit,
    onShowGalleryChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val existingPainter = resolvePainter(imageUrl = existingImageUrl.ifBlank { null }, loadImage = loadImage)
    val picker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                allowMultiple = false,
                mimeTypeMismatchMessage = Strings.AddEditPose.ONLY_ALLOW_PNG,
            )
        )
    )
    val result = picker.result

    LaunchedEffect(showGallery) {
        if (showGallery) {
            picker.launchGallery()
        }
    }

    LaunchedEffect(result) {
        when (val currentResult = result) {
            is ImagePickerResult.Success -> {
                onSelectImage(currentResult.photos)
                onShowGalleryChange(false)
                picker.reset()
            }

            is ImagePickerResult.Error -> {
                onShowGalleryChange(false)
                picker.reset()
            }

            is ImagePickerResult.Dismissed -> {
                onShowGalleryChange(false)
                picker.reset()
            }

            else -> Unit
        }
    }

    Column(modifier = modifier) {
        when {
            selectedImage != null -> {
                ImageBox(
                    onRemove = { onSelectImage(emptyList()) },
                ) {
                    Image(
                        painter = selectedImage.loadPainter()!!,
                        contentDescription = Strings.AddEditPose.POSE_IMAGE,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            existingPainter != null -> {
                ImageBox(
                    onRemove = { onSelectImage(emptyList()) },
                ) {
                    Image(
                        painter = existingPainter,
                        contentDescription = Strings.AddEditPose.POSE_IMAGE,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            existingImageUrl.isNotBlank() -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 280.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.medium,
                        ),
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(56.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { onShowGalleryChange(true) },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp),
        ) {
            Text(
                text = Strings.AddEditPose.CHOOSE_PHOTO_FROM_GALLERY,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun PoseCategory(
    isCategoryChosen: (String) -> Boolean,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier) {
        items(items = PoseCategory.entries) { category ->
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isCategoryChosen(category.name)) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onBackground
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(
                        color = if (isCategoryChosen(category.name)) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        },
                        shape = MaterialTheme.shapes.small,
                    )
                    .clickable { onCategoryClick(category.name) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
    }
}

@Composable
private fun PoseDifficultyContent(
    difficulty: Int,
    onDifficultyChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                imageVector = TablerIcons.Star,
                contentDescription = Strings.Common.POSE_DIFFICULTY,
                tint = if (difficulty > 0) {
                    FlameColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (difficulty > 0) {
                    "Difficulty: $difficulty/10"
                } else {
                    "Difficulty: –/10"
                },
                style = MaterialTheme.typography.titleSmall,
                color = if (difficulty > 0) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                },
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = difficulty.toFloat(),
            onValueChange = { onDifficultyChange(it.toInt()) },
            valueRange = 0f..10f,
            steps = 0,
            colors = SliderDefaults.colors(
                thumbColor = if (difficulty > 0) {
                    FlameColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
                activeTrackColor = if (difficulty > 0) {
                    FlameColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun UserPersonalScoreContent(
    personalScore: Int,
    onPersonalScoreChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                imageVector = TablerIcons.Star,
                contentDescription = Strings.Common.POSE_PERSONAL_SCORE,
                tint = if (personalScore > 0) {
                    GoldenColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
                modifier = Modifier.size(20.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (personalScore > 0) {
                    "Your score: $personalScore/10"
                } else {
                    "Your score: –/10"
                },
                style = MaterialTheme.typography.titleSmall,
                color = if (personalScore > 0) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                },
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = personalScore.toFloat(),
            onValueChange = { onPersonalScoreChange(it.toInt()) },
            valueRange = 0f..10f,
            steps = 0,
            colors = SliderDefaults.colors(
                thumbColor = if (personalScore > 0) {
                    GoldenColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
                activeTrackColor = if (personalScore > 0) {
                    GoldenColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                },
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun UserCommentsContent(
    userComments: String,
    onUserCommentsChange: (String) -> Unit,
    onDoneButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ProjectOutlinedTextField(
        value = userComments,
        onValueChange = onUserCommentsChange,
        label = Strings.Common.USER_COMMENTS_LABEL,
        valueMaxLength = USER_COMMENTS_MAX_LENGTH,
        maxLines = 5,
        keyboardOptions = KeyboardOptions(
            imeAction = Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneButtonClick()
            },
        ),
        modifier = modifier
            .fillMaxWidth(),
    )
}

@Preview
@Composable
fun AddPoseScreenPreview() {
    AddPoseScreen(
        state = AddPoseState(),
        loadImage = { null },
        onNameChange = {},
        onDescriptionChange = {},
        onCategoryClick = {},
        onDifficultyChange = {},
        onPersonalScoreChange = {},
        onUserCommentsChange = {},
        onImageSelected = {},
        onImageRemoved = {},
        onBackClick = {},
        onSavePoseClick = {},
    )
}
