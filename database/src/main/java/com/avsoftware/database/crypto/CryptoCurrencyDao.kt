package com.avsoftware.database.crypto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {
    @Query("SELECT * FROM crypto_currency")
    fun getAll(): Flow<List<CryptoCurrencyEntity>>

    @Query("SELECT * FROM crypto_currency WHERE symbol LIKE :search")
    fun findByName(search: String): List<CryptoCurrencyEntity>

    @Insert
    fun insertAll(symbols: List<CryptoCurrencyEntity>)

    @Upsert
    fun upsertAll(symbols: List<CryptoCurrencyEntity>)

    @Upsert
    fun upsert(symbol: CryptoCurrencyEntity)

    @Delete
    fun delete(symbol: CryptoCurrencyEntity)
}