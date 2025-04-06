package com.avsoftware.domain.stocks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stock(
    val symbol: String,
    val name: String?,
    val price: Double?, // BigDecimal?
    val exchange: String?
): Parcelable