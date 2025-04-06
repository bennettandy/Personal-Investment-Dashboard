package com.avsoftware.domain.fmp.search

import com.avsoftware.model.StockSymbol
import kotlinx.coroutines.flow.Flow

interface GetStockSymbolsUseCase {
    fun getSelectedStockSymbols(): Flow<List<StockSymbol>>
}