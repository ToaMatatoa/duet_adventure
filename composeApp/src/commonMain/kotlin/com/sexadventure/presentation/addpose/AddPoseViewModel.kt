package com.sexadventure.presentation.addpose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.MAX_VALUE
import com.sexadventure.MIN_VALUE
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.model.displayName
import com.sexadventure.domain.usecase.SavePoseUseCase
import com.sexadventure.storage.ImageStorage
import com.sexadventure.storage.LOCAL_IMAGE_PREFIX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AddPoseViewModel(
    private val imageStorage: ImageStorage,
    private val savePoseUseCase: SavePoseUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<AddPoseState> = MutableStateFlow(value = AddPoseState())
    val state: StateFlow<AddPoseState> = _state.asStateFlow()

    /** Stores raw image bytes from the gallery picker for later persistence */
    private var pendingImageBytes: ByteArray? = null

    fun onImageSelected(bytes: ByteArray?) {
        pendingImageBytes = bytes
    }

    fun onImageRemoved() {
        pendingImageBytes = null
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    fun onCategoryClick(category: String) {
        val allName = PoseCategory.ALL.name
        val nonAllCategories =
            PoseCategory.entries
                .filter { it != PoseCategory.ALL }
                .map { it.name }
        val current = _state.value.categories

        val updated = when {
            category == allName -> {
                if (current.contains(allName)) {
                    emptyList()
                } else {
                    listOf(allName)
                }
            }

            current.contains(category) -> {
                current - category
            }

            else -> {
                val newList = (current - allName) + category
                if (nonAllCategories.all { it in newList }) {
                    listOf(allName)
                } else {
                    newList
                }
            }
        }

        _state.value = _state.value.copy(categories = updated)
    }

    fun onDifficultyChange(difficulty: Int) {
        _state.value = _state.value.copy(difficulty = difficulty)
    }

    fun onPersonalScoreChange(personalScore: Int) {
        _state.value = _state.value.copy(personalScore = personalScore)
    }

    fun onUserCommentChange(userComment: String) {
        _state.value = _state.value.copy(userComment = userComment)
    }

    @OptIn(ExperimentalUuidApi::class)
    fun savePose() {
        viewModelScope.launch {
            val imageUrl = pendingImageBytes?.let { bytes ->
                val fileName = "user_img_${Uuid.random()}.jpg"
                imageStorage.saveImage(bytes, fileName)
                "$LOCAL_IMAGE_PREFIX$fileName"
            } ?: ""

            val pose = PoseData(
                name = _state.value.name,
                description = _state.value.description,
                imageUrl = imageUrl,
                category = _state.value.categoryDisplay,
                difficulty = _state.value.difficulty,
                personalScore = _state.value.personalScore,
                userComments = _state.value.userComment,
                isUserCreated = true,
            )
            savePoseUseCase(pose)
        }
    }
}

data class AddPoseState(
    val name: String = "",
    val description: String = "",
    val categories: List<String> = emptyList(),
    val difficulty: Int = 0,
    val personalScore: Int = 0,
    val userComment: String = "",
) {
    val categoryDisplay: String
        get() {
            val allName = PoseCategory.ALL.name
            val names = if (categories.contains(allName)) {
                PoseCategory.entries
                    .filter { it != PoseCategory.ALL }
                    .map { it.displayName() }
            } else {
                categories.mapNotNull { name ->
                    PoseCategory.entries
                        .firstOrNull { it.name == name }
                        ?.displayName()
                }
            }
            return names.joinToString(separator = ", ")
        }

    val isValid: Boolean
        get() =
            name.isNotBlank() && categories.isNotEmpty() &&
                difficulty in MIN_VALUE..MAX_VALUE
}
