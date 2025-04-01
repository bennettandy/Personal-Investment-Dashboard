package com.avsoftware.domain.developer

data class FailureInjectorConfig(
    val enabled: Boolean,
    val failurePercentage: Int, // Float?
    val delaySeconds:  Int // 0 - 10
)