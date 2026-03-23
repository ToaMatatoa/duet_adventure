package com.sexadventure.domain.model

enum class PoseCategory {
    ALL,
    CLASSIC,
    ANAL,
    ORAL,
}

fun PoseCategory.displayName(): String = when (this) {
    PoseCategory.ALL -> "All"
    PoseCategory.CLASSIC -> "Classic"
    PoseCategory.ANAL -> "Anal"
    PoseCategory.ORAL -> "Oral"
}
