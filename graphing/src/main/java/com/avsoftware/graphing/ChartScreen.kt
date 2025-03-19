package com.avsoftware.graphing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.avsoftware.core.ui.RoundedCornerPanel

@Composable
fun ChartScreen(
    modifier: Modifier = Modifier
){
    RoundedCornerPanel(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "My text")
    }
}
