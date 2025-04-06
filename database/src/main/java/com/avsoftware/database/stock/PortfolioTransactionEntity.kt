package com.avsoftware.database.stock

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity(
    tableName = "portfolio_transactions",
    foreignKeys = [ForeignKey(
        entity = StockSymbolEntity::class,
        parentColumns = ["symbol"],
        childColumns = ["assetKey"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PortfolioTransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val assetKey: String, // References StockSymbol.symbol
    val transactionDate: OffsetDateTime,
    // ... other fields
)