package com.sexadventure.core.datasource

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

actual class ImageStorage(
    private val context: Context,
) {
    private fun userImagesDir(): File {
        val dir = File(context.filesDir, "user_images")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    actual suspend fun saveImage(
        bytes: ByteArray,
        fileName: String,
    ): String =
        withContext(Dispatchers.IO) {
            val file = File(userImagesDir(), fileName)
            file.writeBytes(bytes)
            fileName
        }

    actual suspend fun loadImageBytes(fileName: String): ByteArray? =
        withContext(Dispatchers.IO) {
            val file = File(userImagesDir(), fileName)
            if (file.exists()) file.readBytes() else null
        }

    actual suspend fun deleteImage(fileName: String) {
        withContext(Dispatchers.IO) {
            File(userImagesDir(), fileName).delete()
        }
    }
}
