package com.sexadventure.presentation.addpose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sexadventure.MAX_VALUE
import com.sexadventure.MIN_VALUE
import com.sexadventure.designsystem.strings.Strings
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.model.displayName
import com.sexadventure.domain.usecase.GetPoseByIdUseCase
import com.sexadventure.domain.usecase.SavePoseUseCase
import com.sexadventure.domain.usecase.imagestorage.GetImageUseCase
import com.sexadventure.domain.usecase.imagestorage.SaveImageUseCase
import com.sexadventure.logic.LOCAL_IMAGE_PREFIX
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AddPoseViewModel(
    private val poseId: Int,
    private val getPoseByIdUseCase: GetPoseByIdUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val savePoseUseCase: SavePoseUseCase,
    private val saveImageUseCase: SaveImageUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<AddPoseState> = MutableStateFlow(
        value = AddPoseState(isEditMode = poseId > 0),
    )
    val state: StateFlow<AddPoseState> = _state.asStateFlow()

    private val _effects = Channel<AddPoseEffects>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        if (poseId > 0) {
            loadPose()
        }
    }

    suspend fun loadImage(imageName: String): ByteArray? = getImageUseCase(imageName)

    private fun loadPose() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val pose = getPoseByIdUseCase(id = poseId)
            if (pose != null) {
                _state.update {
                    it.copy(
                        pose = pose,
                        isLoading = false,
                        name = pose.name,
                        description = pose.description,
                        imageUrl = pose.imageUrl,
                        categories = parseCategoriesFromDisplayString(categoryDisplay = pose.category),
                        difficulty = pose.difficulty,
                        personalScore = pose.personalScore,
                        userComment = pose.userComments,
                    )
                }
            } else {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun parseCategoriesFromDisplayString(categoryDisplay: String): List<String> {
        if (categoryDisplay.isBlank()) return emptyList()
        val displayNames = categoryDisplay.split(", ")
        val categoryNames = displayNames.mapNotNull { displayName ->
            PoseCategory.entries.firstOrNull { it.displayName() == displayName }?.name
        }
        val nonAllCategoryNames = PoseCategory.entries.filter { it != PoseCategory.ALL }.map { it.name }
        return if (nonAllCategoryNames.all { it in categoryNames }) {
            listOf(PoseCategory.ALL.name)
        } else {
            categoryNames
        }
    }

    /** Stores raw image bytes from the gallery picker for later persistence */
    private var pendingImageBytes: ByteArray? = null

    fun onImageSelected(bytes: ByteArray?) {
        pendingImageBytes = bytes
    }

    fun onImageRemoved() {
        pendingImageBytes = null
        _state.update { it.copy(imageUrl = "") }
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
            val currentState = _state.value
            val imageUrl = pendingImageBytes?.let { bytes ->
                val fileName = "user_img_${Uuid.random()}.jpg"
                saveImageUseCase.invoke(imageData = bytes, imageName = fileName)
                "$LOCAL_IMAGE_PREFIX$fileName"
            } ?: currentState.imageUrl

            val pose = PoseData(
                id = if (currentState.isEditMode) poseId else 0,
                name = currentState.name,
                description = currentState.description,
                imageUrl = imageUrl,
                category = currentState.categoryDisplay,
                difficulty = currentState.difficulty,
                personalScore = currentState.personalScore,
                userComments = currentState.userComment,
                isUserCreated = true,
            )
            savePoseUseCase(poseData = pose)

            if (currentState.isEditMode) {
                _effects.send(AddPoseEffects.ShowMessage(message = Strings.AddEditPose.POSE_UPDATED))
                _effects.send(AddPoseEffects.NavigateToAllPoses)
            } else {
                _effects.send(AddPoseEffects.ShowMessage(message = Strings.AddEditPose.POSE_SAVED))
                _effects.send(AddPoseEffects.BackToHome)
            }
        }
    }
}

data class AddPoseState(
    val pose: PoseData? = null,
    val isLoading: Boolean = false,
    val isEditMode: Boolean = false,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
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
