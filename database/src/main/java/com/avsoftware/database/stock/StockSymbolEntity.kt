package com.avsoftware.database.stock

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_symbols")
data class StockSymbolEntity(
    @PrimaryKey val symbol: String,
    val name: String,
    val currency: String,
    val exchangeFullName: String,
    val exchange: String
)
