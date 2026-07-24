package com.sexadventure.domain.usecase.imagestorage

import com.sexadventure.core.repository.ImageStorageRepository

class SaveImageUseCase(
    private val imageStorageRepository: ImageStorageRepository,
) {
    suspend operator fun invoke(
        imageData: ByteArray,
        imageName: String,
    ): String = imageStorageRepository.saveImage(imageData = imageData, imageName = imageName)
}
