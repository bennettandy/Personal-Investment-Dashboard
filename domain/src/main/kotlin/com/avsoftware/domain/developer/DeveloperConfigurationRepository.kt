package com.avsoftware.domain.developer

import kotlinx.coroutines.flow.StateFlow

interface DeveloperConfigurationRepository {
    fun getFailureInjectorConfig(): FailureInjectorConfig?
    fun setFailureInjectorConfig(config: FailureInjectorConfig)
    val config: StateFlow<FailureInjectorConfig?>
}