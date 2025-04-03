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
import com.avsoftware.search.StockSymbolsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DashboardScreen(
    modifier: Modifier = Modifier,
    uiState: StockSymbolsUiState,
    navigateToDetails: () -> Unit
){

    RoundedCornerPanel(
        modifier = modifier.padding(LocalSpacing.current.medium),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {

            item {
                Text(
                    modifier = Modifier.clickable { navigateToDetails() },
                    text = "Dashboard Screen",
                    style = MaterialTheme.typography.headlineLarge
                )

                MediumHorizontalSpacer()
            }

            uiState.tickerList.forEach {
                item {
                    Text(text = "${it.symbol}")
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(LocalSpacing.current.medium),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SimplePieChart(modifier = Modifier)
                }
            }

            item {
                Text(
                    modifier = Modifier.clickable { navigateToDetails() },
                    text = "Dashboard Screen2"
                )
            }

            item {
                ThermometerProgressBarWithLabel(
                    percentage = 0.25f
                )
            }

            item {
                LinearDeterminateIndicator(
                )
            }
        }
    }
}

@Composable
fun ThermometerProgressBarWithLabel(percentage: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { percentage },
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = Color.Green,
            gapSize = 1.dp
        )

        Text(
            text = "${percentage.toInt()}%",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun LinearDeterminateIndicator() {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false // Reset loading when the coroutine finishes
            }
        }, enabled = !loading) {
            Text("Start loading")
        }

        if (loading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}