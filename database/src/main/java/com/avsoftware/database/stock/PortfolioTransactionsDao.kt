package com.avsoftware.database.stock

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioTransactionsDao {
    @Query("SELECT * FROM portfolio_transactions")
    fun getAll(): Flow<List<PortfolioTransactionEntity>>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: StringArray): List<User>

//    @Query("SELECT * FROM portfolio_transactions WHERE symbol LIKE :search")
//    fun findByName(search: String): List<PortfolioTransactionEntity>

    @Insert
    fun insertAll(symbols: List<PortfolioTransactionEntity>)

    @Upsert
    fun upsert(symbol: PortfolioTransactionEntity)

    @Delete
    fun delete(symbol: PortfolioTransactionEntity)
}