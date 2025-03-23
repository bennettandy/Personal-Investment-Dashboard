package com.avsoftware.core.ui.colour

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colours(
    val pieChartColors: List<Color> = listOf(
        Color(0xFFF44336), // Red
        Color(0xFF2196F3), // Blue
        Color(0xFF4CAF50), // Green
        Color(0xFFFFEB3B), // Yellow
        Color(0xFF9C27B0), // Purple
        Color(0xFFFF9800), // Orange
        Color(0xFF26A69A), // Teal
        Color(0xFFE91E63), // Pink
        Color(0xFF3F51B5), // Indigo
        Color(0xFFCDDC39)  // Lime
    )
)

val LocalColours = compositionLocalOf { Colours() }
