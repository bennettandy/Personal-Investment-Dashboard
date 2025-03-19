package com.avsoftware.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.avsoftware.core.annotation.PreviewUiModes
import com.avsoftware.core.ui.spacing.LocalSpacing
import com.avsoftware.core_ui.theme.DashboardAppTheme


@Composable
fun RoundedCornerPanel(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = spacing.medium,
                shape = MaterialTheme.shapes.medium,
                clip = true
            ),
        color = color,
    ) {
        Box(modifier = Modifier.padding(spacing.medium) ){
            content()
        }
    }
}

@PreviewUiModes
@Composable
fun RoundedCornerPanelPreview(){
    DashboardAppTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background))
        RoundedCornerPanel(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.large),
            color = MaterialTheme.colorScheme.primaryContainer) {
            Text(modifier = Modifier.padding(16.dp), text = "Preview")
        }
    }
}