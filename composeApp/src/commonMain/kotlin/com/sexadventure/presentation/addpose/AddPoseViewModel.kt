package com.sexadventure.presentation.addpose

import androidx.lifecycle.ViewModel
import com.sexadventure.MAX_VALUE
import com.sexadventure.MIN_VALUE
import com.sexadventure.domain.model.PoseCategory
import com.sexadventure.domain.model.PoseData
import com.sexadventure.domain.model.displayName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddPoseViewModel : ViewModel() {
    private val _state: MutableStateFlow<AddPoseState> = MutableStateFlow(value = AddPoseState())
    val state: StateFlow<AddPoseState> = _state.asStateFlow()

    fun onImageUrlChange(imageUrl: String) {
        _state.value = _state.value.copy(imageUrl = imageUrl)
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

    fun savePose() {
        PoseData(
            name = _state.value.name,
            description = _state.value.description,
            imageUrl = _state.value.imageUrl,
            category = _state.value.categories.joinToString(separator = ","),
            difficulty = _state.value.difficulty,
            personalScore = _state.value.personalScore,
            isUserCreated = true,
        )
    }
}

data class AddPoseState(
    val imageUrl: String = "",
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
                difficulty in MIN_VALUE..MAX_VALUE && personalScore in MIN_VALUE..MAX_VALUE
}
