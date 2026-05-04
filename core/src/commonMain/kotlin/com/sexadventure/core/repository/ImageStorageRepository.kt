package com.sexadventure.core.repository

interface ImageStorageRepository {
    suspend fun saveImage(
        imageData: ByteArray,
        imageName: String,
    ): String

    suspend fun deleteImage(imageName: String)

    suspend fun getImage(imageName: String): ByteArray?
}

class ImageStorageRepositoryImpl(
    private val imageStorage: com.sexadventure.core.datasource.ImageStorage,
) : ImageStorageRepository {
    override suspend fun saveImage(
        imageData: ByteArray,
        imageName: String,
    ): String = imageStorage.saveImage(bytes = imageData, fileName = imageName)

    override suspend fun deleteImage(imageName: String) = imageStorage.deleteImage(fileName = imageName)

    override suspend fun getImage(imageName: String): ByteArray? = imageStorage.loadImageBytes(fileName = imageName)
}
