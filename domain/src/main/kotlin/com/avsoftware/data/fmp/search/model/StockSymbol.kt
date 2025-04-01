package com.avsoftware.data.fmp.search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockSymbol
    (
    val symbol: String,
    val name: String,
    val currency: String,
    val exchangeFullName: String,
    val exchange: String
): Parcelable