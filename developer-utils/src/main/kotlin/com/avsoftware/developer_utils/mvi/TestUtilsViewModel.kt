package com.evel.test_utils.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evel.domain.developer.model.DebugInfoConfig
import com.evel.domain.developer.model.DebugInfoConfigurationRepository
import com.evel.domain.developer.model.DeveloperConfigurationRepository
import com.evel.domain.developer.model.FailureInjectorConfig
import com.evel.domain.feature.FeatureFlag
import com.evel.test_utils.model.UiFeatureFlag
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import timber.log.Timber

class TestUtilsViewModel(
    private val developerConfigurationRepository: DeveloperConfigurationRepository,
    private val debugInfoConfigurationRepository: DebugInfoConfigurationRepository,
    private val featureFlags: Map<String,FeatureFlag>
) : ViewModel(), ContainerHost<TestUtilsUiState, TestUtilsSideEffect> {

    // container exposes UI state flow and side effect flows
    override val container =
        viewModelScope.container<TestUtilsUiState, TestUtilsSideEffect>(TestUtilsUiState.default)

    init {
        handleLoadScreen()
    }

    fun handleIntent(intent: TestUtilsIntent) {
        when (intent) {
            is TestUtilsIntent.LoadScreen -> handleLoadScreen()
            is TestUtilsIntent.UpdateFailureConfig -> handleUpdateFailureConfig(intent.failureInjectorConfig)
            is TestUtilsIntent.UpdateDebugInfoConfig -> handleUpdateDebugInfoConfig(intent.debugInfoConfig)
        }
    }

    private fun handleLoadScreen() = intent {
        featureFlags.keys.map { UiFeatureFlag(
            name = it,
            details = featureFlags[it]?.featureFlagName.orEmpty(),
            isEnabled = featureFlags[it]?.isEnabled() ?: false,
        ) }.toList().let {
            reduce {
                state.copy(featureFlags = it )
            }
        }
    }

    private fun handleUpdateFailureConfig(failureInjectorConfig: FailureInjectorConfig) = intent {
        developerConfigurationRepository.setFailureInjectorConfig(failureInjectorConfig)
        reduce {
            state.copy(
                failureInjectorConfig = failureInjectorConfig
            )
        }
    }

    private fun handleUpdateDebugInfoConfig(debugInfoConfig: DebugInfoConfig) = intent {
        debugInfoConfigurationRepository.setDebugInfoConfig(debugInfoConfig)
        Timber.d("XXXX Update $debugInfoConfig")
        reduce {
            state.copy(
                debugInfoConfig = debugInfoConfig
            )
        }
    }
}