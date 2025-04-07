package com.avsoftware.data.fmp.crypto

import com.avsoftware.core.DomainResult
import com.avsoftware.database.crypto.CryptoCurrencyDao
import com.avsoftware.database.crypto.CryptoCurrencyEntity
import com.avsoftware.database.crypto.toEntity
import com.avsoftware.domain.fmp.crypto.CryptoCurrenciesRepository
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import com.avsoftware.domain.fmp.crypto.GetCryptoCurrenciesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class RoomCryptoCurrenciesRepository @Inject constructor(
    private val getCryptoCurrenciesUseCase: GetCryptoCurrenciesUseCase,
    private val cryptoCurrencyDao: CryptoCurrencyDao
)
    : CryptoCurrenciesRepository {

    override fun getDataFlow(): Flow<List<CryptoCurrency>> =
        cryptoCurrencyDao.getAll().map { list ->  list.map { it.toDomain()} }


    override suspend fun refreshFromApi() {
        when (val result = getCryptoCurrenciesUseCase()){
            is DomainResult.Success -> {
                // update database
                cryptoCurrencyDao.upsertAll(result.data.map { it.toEntity() })
            }

            is DomainResult.Error -> Timber.e("Failed to Update Crypto Currencies")
        }
    }

    private fun CryptoCurrencyEntity.toDomain() = CryptoCurrency(
        name = name,
        symbol = symbol,
        exchange = exchange,
        icoDate = icoDate,
        totalSupply = totalSupply,
        circulatingSupply = circulatingSupply
    )

}