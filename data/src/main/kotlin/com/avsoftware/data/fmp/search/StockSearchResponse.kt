package com.avsoftware.data.fmp.search

import com.avsoftware.model.StockSymbol
import com.avsoftware.database.stock.StockSymbolEntity

data class StockSearchResponse(
    val symbol: String,
    val name: String,
    val currency: String,
    val exchangeFullName: String,
    val exchange: String
)

fun StockSearchResponse.toDomain() = StockSymbol(
    symbol, name, currency, exchangeFullName, exchange
)

fun StockSearchResponse.toEntity() = StockSymbolEntity(
    symbol, name, currency, exchangeFullName, exchange
)
