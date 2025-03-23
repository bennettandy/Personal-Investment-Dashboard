package com.avsoftware.data.fmp.search

import com.avsoftware.data.fmp.search.model.StockSymbol

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
