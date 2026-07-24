package com.sexadventure.domain.usecase.imagestorage

import com.sexadventure.core.repository.ImageStorageRepository

class DeleteImageUseCase(
    private val imageStorageRepository: ImageStorageRepository,
) {
    suspend operator fun invoke(imageName: String) = imageStorageRepository.deleteImage(imageName = imageName)
}
