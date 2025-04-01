package com.evel.test_utils.mvi

import com.evel.domain.developer.model.DebugInfoConfig
import com.evel.domain.developer.model.FailureInjectorConfig

sealed interface TestUtilsIntent {
    data object LoadScreen : TestUtilsIntent
    data class UpdateFailureConfig( val failureInjectorConfig: FailureInjectorConfig) : TestUtilsIntent
    data class UpdateDebugInfoConfig( val debugInfoConfig: DebugInfoConfig): TestUtilsIntent
}