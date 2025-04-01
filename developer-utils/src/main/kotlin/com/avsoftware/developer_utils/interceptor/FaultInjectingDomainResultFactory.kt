package com.avsoftware.developer_utils.interceptor

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainError
import com.avsoftware.core.DomainResult
import com.avsoftware.domain.DomainResultFactory

import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class FaultInjectingDomainResultFactory(
    private val developerConfigurationRepository: DeveloperConfigurationRepository
): DomainResultFactory {

    override suspend fun <D> createSuccess(payload: D): DomainResult<D, DomainError> {
        return if (isEnabled()) {
            return if (errorFunction()) {
                DomainResult.Error(ApiError("Deliberate Injected Fail"))
            } else {
                DomainResult.Success(payload)
            }
        }
        else DomainResult.Success(payload)
    }

    override suspend fun <E : DomainError> createError(error: E): DomainResult.Error<E> {
        return DomainResult.Error(error)
    }

    override suspend fun <D> wrapOperation(operation: suspend () -> DomainResult<D, DomainError>): DomainResult<D, DomainError> {
        if (isEnabled()) {
            return if (errorFunction()) {
                DomainResult.Error(ApiError("Deliberate Injected Fail"))
            } else {
                return operation()
            }
        }
        else return operation()
    }

    private fun isEnabled() = developerConfigurationRepository.config.value?.enabled == true

    private suspend fun errorFunction(): Boolean {
        Timber.d("xxx Error function with ${developerConfigurationRepository.config.value}")
        with(developerConfigurationRepository.config){
            value?.delaySeconds?.let {
                // delay the response
                delay(duration = it.seconds)
            }
            return if (value?.failurePercentage != 0) {
                // Decide whether to fail based on the failure percentage
                Random.nextInt(until = 100) < (value?.failurePercentage ?: 0)
            } else {
                false // No failure if percentage is 0
            }
        }
    }
}