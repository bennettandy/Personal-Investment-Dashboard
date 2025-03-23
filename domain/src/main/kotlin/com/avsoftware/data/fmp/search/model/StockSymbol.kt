package com.avsoftware.data.fmp.search.model

data class StockSymbol
    (
    val symbol: String,
    val name: String,
    val currency: String,
    val exchangeFullName: String,
    val exchange: String
)