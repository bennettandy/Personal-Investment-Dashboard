package com.avsoftware.data.fmp.search

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainResult
import com.avsoftware.data.fmp.search.model.StockSymbol

interface StockSymbolSearch {
    suspend fun searchSymbol(searchQuery: String): DomainResult<List<StockSymbol>, ApiError>
}