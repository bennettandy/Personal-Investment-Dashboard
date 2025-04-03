package com.avsoftware.database.stock

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface StockSymbolDao {
    @Query("SELECT * FROM stock_symbols")
    fun getAll(): Flow<List<StockSymbolEntity>>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: StringArray): List<User>

    @Query("SELECT * FROM stock_symbols WHERE symbol LIKE :search")
    fun findByName(search: String): List<StockSymbolEntity>

    @Insert
    fun insertAll(symbols: List<StockSymbolEntity>)

    @Upsert
    fun upsert(symbol: StockSymbolEntity)

    @Delete
    fun delete(symbol: StockSymbolEntity)
}