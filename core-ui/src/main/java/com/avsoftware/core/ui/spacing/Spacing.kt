package com.avsoftware.core.ui.spacing

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = SpacingDefaults.DEFAULT.dp,
    val tiny: Dp = SpacingDefaults.TINY.dp,
    val extraSmall: Dp = SpacingDefaults.EXTRA_SMALL.dp,
    val small: Dp = SpacingDefaults.SMALL.dp,
    val medium: Dp = SpacingDefaults.MEDIUM.dp,
    val large: Dp = SpacingDefaults.LARGE.dp,
    val xLarge: Dp = SpacingDefaults.X_LARGE.dp,
    val xxLarge: Dp = SpacingDefaults.XX_LARGE.dp,

    val iconSmall: Dp = SpacingDefaults.ICON_SMALL.dp,
    val iconMedium: Dp = SpacingDefaults.ICON_MEDIUM.dp,
    val iconLarge: Dp = SpacingDefaults.ICON_LARGE.dp,

    val indicatorTiny: Dp = SpacingDefaults.INDICATOR_TINY.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

