package com.avsoftware.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.avsoftware.core_ui.theme.DashboardAppTheme
import com.avsoftware.graphing.animations.FinancesLoadingLottieAnim

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DashboardAppTheme {
                FinancesLoadingLottieAnim(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}