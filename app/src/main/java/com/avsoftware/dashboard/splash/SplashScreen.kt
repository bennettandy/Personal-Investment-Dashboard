package com.avsoftware.dashboard.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.avsoftware.graphing.animations.FinancesLoadingLottieAnim
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashCompleted: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LaunchedEffect(true) {
            delay(2.seconds)
            splashCompleted()
        }
        FinancesLoadingLottieAnim(
            modifier = Modifier.fillMaxSize()
        )
    }
}