package com.avsoftware.developer_utils.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SliderView(
    modifier: Modifier = Modifier,
    label: String,
    quantityLabel: String,
    enabled: Boolean = true,
    maxValue: Int,
    minValue: Int,
    currentValue: Int,
    onValueChanged: (Int) -> Unit
){
    // is this necessary when we have state in ViewModel?
    var localState by remember { mutableIntStateOf(currentValue) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label)
        Slider(
            value = localState.toFloat(),
            onValueChange = { newValue -> localState = newValue.toInt()
                onValueChanged(localState)
            },
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            steps = 9, // 0-10 is 11 steps, so 9 steps between them
            enabled = enabled,
        )
        Text("$localState $quantityLabel")
    }
}