package com.avsoftware.dashboard.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.avsoftware.core.ui.RoundedCornerPanel
import com.avsoftware.core.ui.spacing.LocalSpacing
import com.avsoftware.core.ui.spacing.MediumHorizontalSpacer
import com.avsoftware.domain.fmp.crypto.CryptoCurrency

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DashboardRoomScreen(
    modifier: Modifier = Modifier,
    cryptoCurrencyList: List<CryptoCurrency>,
    navigateToDetails: () -> Unit
) {
    RoundedCornerPanel(
        modifier = modifier.padding(LocalSpacing.current.medium),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {

            item {
                Text(
                    modifier = Modifier.clickable { navigateToDetails() },
                    text = "Dashboard Room database",
                    style = MaterialTheme.typography.headlineLarge
                )

                MediumHorizontalSpacer()
            }

            cryptoCurrencyList.forEach {
                item {
                    Text(text = "${it.symbol} : ${it.name} : ${it.exchange}")
                }
            }
        }
    }
}