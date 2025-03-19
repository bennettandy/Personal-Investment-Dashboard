package com.avsoftware.core.ui.spacing

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SmallHorizontalSpacer() = HorizontalSpacer(height = LocalSpacing.current.small)

@Composable
fun MediumHorizontalSpacer() = HorizontalSpacer(height = LocalSpacing.current.medium)

@Composable
fun LargeHorizontalSpacer() = HorizontalSpacer(height = LocalSpacing.current.large)

@Composable
fun SmallVerticalSpacer() = VerticalSpacer(width = LocalSpacing.current.small)

@Composable
fun MediumVerticalSpacer() = VerticalSpacer(width = LocalSpacing.current.medium)

@Composable
fun LargeVerticalSpacer() = VerticalSpacer(width = LocalSpacing.current.large)


@Composable
private fun HorizontalSpacer(height: Dp) = Spacer(modifier = Modifier.height(height))

@Composable
private fun VerticalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

