package com.sexadventure.presentation.addpose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddPoseViewModel : ViewModel() {
    private val _state: MutableStateFlow<AddPoseState> = MutableStateFlow(value = AddPoseState())
    val state: StateFlow<AddPoseState> = _state.asStateFlow()
}

data class AddPoseState(
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val imageUrl: String = "",
) {
    val isValid: Boolean
        get() = name.isNotBlank() && description.isNotBlank() && category.isNotBlank() && imageUrl.isNotBlank()
}
