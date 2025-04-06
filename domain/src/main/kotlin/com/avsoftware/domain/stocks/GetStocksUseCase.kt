package com.avsoftware.domain.stocks

import com.avsoftware.core.DomainError
import com.avsoftware.core.DomainResult

interface GetStocksUseCase {
    suspend operator fun invoke(): DomainResult<List<Stock>, DomainError>
}