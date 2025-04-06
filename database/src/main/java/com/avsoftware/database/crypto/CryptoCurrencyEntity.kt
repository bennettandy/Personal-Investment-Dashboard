package com.avsoftware.database.crypto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import java.math.BigDecimal
import java.time.LocalDate

@Entity(tableName = "crypto_currency")
data class CryptoCurrencyEntity (
    @PrimaryKey val symbol: String,
    val name: String,
    val exchange: String,
    val icoDate: LocalDate?,
    val circulatingSupply: BigDecimal?,
    val totalSupply: BigDecimal?
)

fun CryptoCurrency.toEntity() = CryptoCurrencyEntity(
    symbol, name, exchange, icoDate, circulatingSupply, totalSupply
)