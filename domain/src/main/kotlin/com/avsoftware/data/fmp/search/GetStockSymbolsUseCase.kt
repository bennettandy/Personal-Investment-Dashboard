package com.avsoftware.data.fmp.search

import com.avsoftware.data.fmp.search.model.StockSymbol
import kotlinx.coroutines.flow.Flow

interface GetStockSymbolsUseCase {
    fun getSelectedStockSymbols(): Flow<List<StockSymbol>>
}