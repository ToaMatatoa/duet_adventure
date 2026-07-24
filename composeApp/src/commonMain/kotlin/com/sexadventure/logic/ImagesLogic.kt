package com.sexadventure.logic

/** Prefix used to mark user-saved images in imageUrl field */
const val LOCAL_IMAGE_PREFIX = "local://"

fun isLocalImage(imageUrl: String): Boolean = imageUrl.startsWith(LOCAL_IMAGE_PREFIX)

fun localImageFileName(imageUrl: String): String = imageUrl.removePrefix(LOCAL_IMAGE_PREFIX)
