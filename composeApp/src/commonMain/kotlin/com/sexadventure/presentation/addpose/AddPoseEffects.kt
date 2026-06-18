package com.sexadventure.presentation.addpose

sealed class AddPoseEffects {
    data class ShowMessage(val message: String) : AddPoseEffects()

    data object BackToHome : AddPoseEffects()

    data object NavigateToAllPoses : AddPoseEffects()
}
