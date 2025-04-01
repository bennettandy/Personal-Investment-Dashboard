package com.avsoftware.domain

import com.avsoftware.core.DomainError
import com.avsoftware.core.DomainResult


interface DomainResultFactory {
    suspend fun <D>createSuccess(payload: D): DomainResult<D, DomainError>
    suspend fun <E: DomainError>createError(error: E): DomainResult.Error<E>
    // Use wrapOperation for endpoint calls where we don't have a result mapper from Backbase UseCase
    suspend fun <D>wrapOperation(operation: suspend () -> DomainResult<D, DomainError>): DomainResult<D, DomainError>
}