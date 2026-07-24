package com.sexadventure.presentation.detailpose

sealed class DetailPoseEffects {
    data object ShowMessage : DetailPoseEffects()

    data object BackToHome : DetailPoseEffects()

    data object OpenAddPoseScreen : DetailPoseEffects()
}
