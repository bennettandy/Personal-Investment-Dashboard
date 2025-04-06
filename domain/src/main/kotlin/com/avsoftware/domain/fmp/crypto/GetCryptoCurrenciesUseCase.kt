package com.avsoftware.domain.fmp.crypto

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainResult

interface GetCryptoCurrenciesUseCase {
    suspend operator fun invoke(): DomainResult<List<CryptoCurrency>, ApiError>
}