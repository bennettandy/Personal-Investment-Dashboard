package com.avsoftware.domain.fmp.crypto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate

@Parcelize
data class CryptoCurrency(
    val symbol: String,
    val name: String,
    val exchange: String,
    val icoDate: LocalDate,
    val circulatingSupply: BigDecimal,
    val totalSupply: BigDecimal?
): Parcelable

/*
{
    "symbol": "IOUSD",
    "name": "io.net USD",
    "exchange": "CCC",
    "icoDate": "2024-06-11",
    "circulatingSupply": 110161120,
    "totalSupply": null
}
*/

