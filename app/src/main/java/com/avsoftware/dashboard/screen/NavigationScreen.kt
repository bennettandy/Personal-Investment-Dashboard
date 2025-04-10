package com.avsoftware.dashboard.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avsoftware.core.charts.SimplePieChart
import com.avsoftware.core.ui.RoundedCornerPanel
import com.avsoftware.core.ui.spacing.LocalSpacing
import com.avsoftware.core.ui.spacing.MediumHorizontalSpacer
import com.avsoftware.dashboard.DashboardUiState
import com.avsoftware.search.StockSymbolsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NavigationScreen(
    modifier: Modifier = Modifier,
    uiState: DashboardUiState,
    navigateTo: (String) -> Unit
){
    RoundedCornerPanel(
        modifier = modifier.padding(LocalSpacing.current.medium),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {

            item {
                Text(
                    modifier = Modifier.clickable { navigateTo("") },
                    text = "Dashboard Screen",
                    style = MaterialTheme.typography.headlineLarge
                )
                MediumHorizontalSpacer()
            }

            item {
                NavigationItem("Item One") { navigateTo("") }
            }

        }
    }
}

@Composable
private fun NavigationItem(description: String, onClick: () -> Unit ){
    Button(
        onClick = onClick
    ){
        Text(text = description)
    }
}
