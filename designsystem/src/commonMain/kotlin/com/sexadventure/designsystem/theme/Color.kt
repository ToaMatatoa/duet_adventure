@file:Suppress("MagicNumber")

package com.sexadventure.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ──────────────────────────────────────────────
// Light palette
// ──────────────────────────────────────────────

/** Warm off-white canvas — main screen background */
val LightBackground = Color(0xFFF8F7F4)

/** Cards, bottom sheets, dialogs */
val LightSurface = Color(0xFFFFFFFF)

/** Inactive chips, dividers, subtle containers */
val LightSurfaceVariant = Color(0xFFF0EEEB)

/** Accent buttons, FAB, selected tab icon, links */
val LightPrimary = Color(0xFF6C5CE7)

/** Soft highlight behind icons, selected chip background */
val LightPrimaryContainer = Color(0xFFEDE8FF)

/** Text / icon on top of primary-colored surfaces */
val LightOnPrimary = Color(0xFFFFFFFF)

/** Main body text on background */
val LightOnBackground = Color(0xFF1A1A1A)

/** Secondary text on surface (cards, sheets) */
val LightOnSurface = Color(0xFF3A3A3A)

/** Borders, thin dividers, text-field outlines */
val LightOutline = Color(0xFFD4D2CF)

// ──────────────────────────────────────────────
// Dark palette
// ──────────────────────────────────────────────

/** Dark canvas background */
val DarkBackground = Color(0xFF121212)

/** Cards, bottom sheets, dialogs */
val DarkSurface = Color(0xFF1E1E1E)

/** Inactive chips, dividers, subtle containers */
val DarkSurfaceVariant = Color(0xFF2A2A2A)

/** Accent buttons, FAB, selected tab icon, links (lighter purple for contrast) */
val DarkPrimary = Color(0xFFA29BFE)

/** Soft highlight behind icons, selected chip background */
val DarkPrimaryContainer = Color(0xFF2D2654)

/** Main body text on dark background */
val DarkOnBackground = Color(0xFFF0F0F0)

/** Secondary text on dark surface */
val DarkOnSurface = Color(0xFFB0B0B0)

/** Borders, thin dividers, text-field outlines */
val DarkOutline = Color(0xFF3A3A3A)

// ──────────────────────────────────────────────
// Semantic / custom colors (not part of MaterialTheme.colorScheme)
// Use directly: e.g.  Icon(tint = Heart)
// ──────────────────────────────────────────────

/** Favorite / like icon filled state — same in both themes */
val FlameColor = Color(0xFFFF6B6B)
val GoldenColor = Color(0xFFFFB300)

val LightAppColorScheme = lightColorScheme(
    background = LightBackground,
    surface = LightSurface,
    surfaceVariant = LightSurfaceVariant,
    primary = LightPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimary = LightOnPrimary,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface,
    outline = LightOutline,
)

val DarkAppColorScheme = darkColorScheme(
    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    primary = DarkPrimary,
    primaryContainer = DarkPrimaryContainer,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface,
    outline = DarkOutline,
)

