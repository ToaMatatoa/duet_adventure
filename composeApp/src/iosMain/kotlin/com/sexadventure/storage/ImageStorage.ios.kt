package com.sexadventure.storage

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.Foundation.create
import platform.Foundation.writeToFile
import platform.posix.memcpy

@OptIn(ExperimentalForeignApi::class)
actual class ImageStorage {
    private fun userImagesDir(): String {
        val paths = NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory,
            NSUserDomainMask,
            true,
        )
        val documentsDir = paths.first() as String
        val dir = "$documentsDir/user_images"
        val fileManager = NSFileManager.defaultManager
        if (!fileManager.fileExistsAtPath(dir)) {
            fileManager.createDirectoryAtPath(dir, withIntermediateDirectories = true, attributes = null, error = null)
        }
        return dir
    }

    actual suspend fun saveImage(bytes: ByteArray, fileName: String): String =
        withContext(Dispatchers.IO) {
            val path = "${userImagesDir()}/$fileName"
            val nsData = bytes.usePinned { pinned ->
                NSData.create(bytes = pinned.addressOf(0), length = bytes.size.toULong())
            }
            nsData.writeToFile(path, atomically = true)
            fileName
        }

    actual suspend fun loadImageBytes(fileName: String): ByteArray? =
        withContext(Dispatchers.IO) {
            val path = "${userImagesDir()}/$fileName"
            val nsData = NSData.create(contentsOfFile = path) ?: return@withContext null
            val size = nsData.length.toInt()
            val bytes = ByteArray(size)
            bytes.usePinned { pinned ->
                memcpy(pinned.addressOf(0), nsData.bytes, nsData.length)
            }
            bytes
        }

    actual suspend fun deleteImage(fileName: String) {
        withContext(Dispatchers.IO) {
            val path = "${userImagesDir()}/$fileName"
            NSFileManager.defaultManager.removeItemAtPath(path, error = null)
        }
    }
}
