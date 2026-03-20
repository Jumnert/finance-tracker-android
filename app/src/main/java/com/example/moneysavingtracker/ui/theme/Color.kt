package com.example.moneysavingtracker.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// 💜 LIGHT THEME

val primaryLight = Color(0xFF3B1E54) // deep purple
val onPrimaryLight = Color(0xFFFFFFFF)

val primaryContainerLight = Color(0xFFD4BEE4) // soft lavender
val onPrimaryContainerLight = Color(0xFF3B1E54)

val secondaryLight = Color(0xFF9B7EBD) // muted purple
val onSecondaryLight = Color(0xFFFFFFFF)

val secondaryContainerLight = Color(0xFFEEEEEE) // neutral light
val onSecondaryContainerLight = Color(0xFF3B1E54)

val tertiaryLight = Color(0xFF7F5AA2) // slightly deeper accent
val onTertiaryLight = Color(0xFFFFFFFF)

val errorLight = Color(0xFFD9534F)
val onErrorLight = Color(0xFFFFFFFF)

val backgroundLight = Color(0xFFEEEEEE)
val onBackgroundLight = Color(0xFF3B1E54)

val surfaceLight = Color(0xFFFFFFFF)
val onSurfaceLight = Color(0xFF3B1E54)

val outlineLight = Color(0xFFD4BEE4)


// 🌙 DARK THEME

val primaryDark = Color(0xFFD4BEE4) // soft lavender text
val onPrimaryDark = Color(0xFF3B1E54)

val primaryContainerDark = Color(0xFF3B1E54)
val onPrimaryContainerDark = Color(0xFFFFFFFF)

val secondaryDark = Color(0xFF9B7EBD)
val onSecondaryDark = Color(0xFF1E1230)

val secondaryContainerDark = Color(0xFF4C2A6A)
val onSecondaryContainerDark = Color(0xFFD4BEE4)

val tertiaryDark = Color(0xFFBFA6D9)
val onTertiaryDark = Color(0xFF1E1230)

val errorDark = Color(0xFFFF8A80)
val onErrorDark = Color(0xFF1B0000)

val backgroundDark = Color(0xFF140A1F) // deep purple-black
val onBackgroundDark = Color(0xFFEEEEEE)

val surfaceDark = Color(0xFF1E1230)
val onSurfaceDark = Color(0xFFEEEEEE)

val outlineDark = Color(0xFF4C2A6A)


// 🎨 CARD GRADIENTS (clean + modern)

val CardGradient1 = Brush.linearGradient(
    colors = listOf(
        Color(0xFF1A0A2E),
        Color(0xFF2D1248),
        Color(0xFF3D1A5E),
        Color(0xFF52278A),
        Color(0xFF6B3AA0),
        Color(0xFF8A5CB8),
        Color(0xFFA87FCC),
        Color(0xFFBFA0D8)
    ),
    start = Offset(0f, 0f),
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
)

val CardGradient2 = listOf(
    Color(0xFF9B7EBD),
    Color(0xFFD4BEE4)
)

val CardGradient3 = listOf(
    Color(0xFF7F5AA2),
    Color(0xFF9B7EBD)
)

val CardGradient4 = listOf(
    Color(0xFFD4BEE4),
    Color(0xFFEEEEEE)
)


// 💸 TRANSACTION COLORS (kept readable)

val IncomeGreen = Color(0xFF4CAF50)
val ExpenseRed = Color(0xFFD9534F)


// 🌫 MUTED

val Muted = Color(0xFFEEEEEE)
val MutedForeground = Color(0xFF9B7EBD)