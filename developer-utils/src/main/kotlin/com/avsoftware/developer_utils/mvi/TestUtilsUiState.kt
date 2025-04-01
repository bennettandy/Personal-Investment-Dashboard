package com.evel.test_utils.mvi

import com.evel.domain.developer.model.DebugInfoConfig
import com.evel.domain.developer.model.FailureInjectorConfig
import com.evel.test_utils.model.UiFeatureFlag

data class TestUtilsUiState(
    val isLoading: Boolean,
    val featureFlags: List<UiFeatureFlag>,
    val failureInjectorConfig: FailureInjectorConfig,
    val debugInfoConfig: DebugInfoConfig
){
    companion object {
        val default = TestUtilsUiState(
            isLoading = false,
            featureFlags = emptyList(),
            failureInjectorConfig = FailureInjectorConfig(
                enabled = false,
                delaySeconds = 0,
                failurePercentage = 0
            ),
            debugInfoConfig = DebugInfoConfig()
        )
    }
}
