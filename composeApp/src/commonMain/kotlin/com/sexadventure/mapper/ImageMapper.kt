package com.sexadventure.mapper

import org.jetbrains.compose.resources.DrawableResource
import sexadventure.composeapp.generated.resources.Res
import sexadventure.composeapp.generated.resources.sixty_nine

/**
 * Maps a pose image name (stored in JSON / Room) to a local [DrawableResource].
 *
 * The image name in the database should match the file name in
 * `composeApp/src/commonMain/composeResources/drawable/` without the extension.
 *
 * Example:
 * - DB stores: `"sixty_nine"`
 * - File: `composeResources/drawable/sixty_nine.webp`
 * - Returns: `Res.drawable.sixty_nine`
 *
 * When you add a new image:
 * 1. Drop the `.webp` file into `composeResources/drawable/`
 * 2. Add a mapping entry below
 */
private val imageMap: Map<String, DrawableResource> = mapOf(
    "sixty_nine" to Res.drawable.sixty_nine,
    // "missionary" to Res.drawable.missionary,
    // "cowgirl" to Res.drawable.cowgirl,
    // ... add all 72 poses here after dropping the .webp files
)

/**
 * Resolves a pose image name to a [DrawableResource].
 * Returns `null` if the name is blank or not found → ListItem shows placeholder.
 */
fun resolveImage(imageName: String?): DrawableResource? {
    if (imageName.isNullOrBlank()) return null
    return imageMap[imageName]
}

