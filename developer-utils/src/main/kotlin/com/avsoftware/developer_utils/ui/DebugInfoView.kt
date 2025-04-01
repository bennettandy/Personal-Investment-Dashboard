package com.avsoftware.developer_utils.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.avsoftware.core.ui.spacing.LocalSpacing
import com.avsoftware.developer_utils.R
import com.avsoftware.domain.DebugInfoConfig

@Composable
fun DebugInfoView(
    modifier: Modifier = Modifier,
    debugInfo: DebugInfoConfig,
    onConfigChange: (DebugInfoConfig) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = LocalSpacing.current.medium)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.debug_info_title))
        Checkbox(
            checked = debugInfo.showInfo,
            onCheckedChange = { newValue ->
                onConfigChange(debugInfo.copy(showInfo = newValue))
            }
        )
    }
}