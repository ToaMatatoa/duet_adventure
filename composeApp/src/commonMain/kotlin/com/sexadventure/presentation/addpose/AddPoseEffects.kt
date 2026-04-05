package com.sexadventure.presentation.addpose

sealed class AddPoseEffects {
    data object ShowMessage : AddPoseEffects()

    data object BackToHome : AddPoseEffects()
}
