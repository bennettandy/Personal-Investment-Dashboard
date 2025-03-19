package com.avsoftware.dashboard.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.avsoftware.core.ui.RoundedCornerPanel
import com.avsoftware.core.ui.spacing.LocalSpacing

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DashboardScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: () -> Unit
){

    RoundedCornerPanel(
        modifier = modifier.padding(LocalSpacing.current.medium),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            modifier = Modifier.clickable { navigateToDetails() },
            text = "Dashboard Screen"
        )
    }
}