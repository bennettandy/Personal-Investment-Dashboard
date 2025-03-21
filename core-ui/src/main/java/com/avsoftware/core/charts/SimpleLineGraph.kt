package com.avsoftware.core.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.avsoftware.core.annotation.PreviewUiModes
import com.avsoftware.core_ui.theme.DashboardAppTheme

@Composable
fun SimpleLineGraph(
    modifier: Modifier = Modifier,
    data: List<Float>, //data points
    lineColor: Color = Color.Blue,
    gradientColors: List<Color> = listOf(Color.Blue.copy(alpha = 0.5f), Color.Transparent),
    strokeWidth: Float = 2f
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        // Handle empty or single-point data
        if (data.size < 2) return@Canvas

        // Calculate min and max values for scaling
        val minValue = data.minOrNull() ?: 0f
        val maxValue = data.maxOrNull() ?: 1f
        val valueRange = if (maxValue == minValue) 1f else maxValue - minValue

        // Calculate points
        val stepX = width / (data.size - 1) // Horizontal spacing between points
        val points = data.mapIndexed { index, value ->
            val x = index * stepX
            val y = height - ((value - minValue) / valueRange) * height // Scale Y to fit height
            Offset(x, y)
        }

        // Draw the line
        val linePath = Path().apply {
            moveTo(points[0].x, points[0].y)
            points.drop(1).forEach { point ->
                lineTo(point.x, point.y) // Jagged line (no curves)
            }
        }
        drawPath(
            path = linePath,
            color = lineColor,
            style = Stroke(width = strokeWidth)
        )

        // Draw the gradient fill
        val fillPath = Path().apply {
            moveTo(points[0].x, points[0].y)
            points.drop(1).forEach { point ->
                lineTo(point.x, point.y)
            }
            lineTo(points.last().x, height) // Bottom-right corner
            lineTo(points[0].x, height) // Bottom-left corner
            close()
        }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = gradientColors,
                startY = 0f,
                endY = height
            )
        )
    }
}

// Usage example
@Composable
fun SimpleLineGraphExample(
    modifier: Modifier = Modifier
) {
    SimpleLineGraph(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        data = listOf(10f, 20f, 15f, 25f, 18f),
        lineColor = Color.Blue,
        gradientColors = listOf(Color.Blue.copy(alpha = 0.5f), Color.Transparent),
        strokeWidth = 2.0f
    )
}

@Composable
@PreviewUiModes
fun PreviewSelfServiceAppBar() {
    Column {

        DashboardAppTheme {
            SimpleLineGraphExample()
        }
    }
}