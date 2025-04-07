package com.avsoftware.domain.fmp.crypto

import kotlinx.coroutines.flow.Flow

interface CryptoCurrenciesRepository {
    fun getDataFlow(): Flow<List<CryptoCurrency>>
    suspend fun refreshFromApi()
}