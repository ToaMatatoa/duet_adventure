package com.sexadventure.storage

/**
 * Saves and loads user-selected images to/from app-internal storage.
 *
 * - [saveImage] persists raw bytes and returns a filename like "user_img_<uuid>.jpg"
 * - [loadImageBytes] reads bytes back from that filename
 * - [deleteImage] removes the file when a user-created pose is deleted
 *
 * The returned filename is stored in `PoseEntity.imageUrl` with a "local://" prefix
 * so display code can distinguish predefined drawable keys from user files.
 */
expect class ImageStorage {
    suspend fun saveImage(bytes: ByteArray, fileName: String): String
    suspend fun loadImageBytes(fileName: String): ByteArray?
    suspend fun deleteImage(fileName: String)
}

/** Prefix used to mark user-saved images in imageUrl field */
const val LOCAL_IMAGE_PREFIX = "local://"

fun isLocalImage(imageUrl: String): Boolean = imageUrl.startsWith(LOCAL_IMAGE_PREFIX)

fun localImageFileName(imageUrl: String): String = imageUrl.removePrefix(LOCAL_IMAGE_PREFIX)
