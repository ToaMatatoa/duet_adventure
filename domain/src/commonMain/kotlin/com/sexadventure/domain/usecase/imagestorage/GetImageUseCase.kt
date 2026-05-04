package com.sexadventure.domain.usecase.imagestorage

import com.sexadventure.core.repository.ImageStorageRepository

class GetImageUseCase(
    private val imageStorageRepository: ImageStorageRepository,
) {
    suspend operator fun invoke(imageName: String): ByteArray? = imageStorageRepository.getImage(imageName = imageName)
}
