package com.evel.test_utils.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avsoftware.developer_utils.ui.SliderView
import com.evel.core.ui.banner.HorizontalBanner
import com.evel.core.ui.spacing.LocalSpacing
import com.evel.domain.developer.model.FailureInjectorConfig
import com.evel.test_utils.R

@Composable
fun FailureInjectorConfigView(
    config: FailureInjectorConfig,
    onConfigChange: (FailureInjectorConfig) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            HorizontalBanner(text = stringResource(R.string.test_utils_error_injection))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.test_utils_error_injection_enable))
            Checkbox(
                checked = config.enabled,
                onCheckedChange = { newValue ->
                    onConfigChange(config.copy(enabled = newValue))
                }
            )
        }

        SliderView(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
            enabled = config.enabled,
            currentValue = config.failurePercentage,
            minValue = 0,
            maxValue = 100,
            label = stringResource(R.string.test_utils_error_injection_failure_percentage),
            quantityLabel = stringResource(R.string.test_utils_error_injection_failure_percent),
            onValueChanged = {
                onConfigChange(config.copy(failurePercentage = it))
            }
        )

        SliderView(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
            enabled = config.enabled,
            currentValue = config.delaySeconds,
            minValue = 0,
            maxValue = 10,
            label = stringResource(R.string.test_utils_network_delay),
            quantityLabel = stringResource(R.string.test_utils_network_delay_seconds),
            onValueChanged = {
                onConfigChange(config.copy(delaySeconds = it))
            }
        )
    }
}