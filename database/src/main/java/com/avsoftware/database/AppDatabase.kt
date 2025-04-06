package com.avsoftware.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avsoftware.database.converters.Converters
import com.avsoftware.database.stock.PortfolioTransactionEntity
import com.avsoftware.database.stock.PortfolioTransactionsDao
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.database.stock.StockSymbolEntity
import com.avsoftware.database.user.User
import com.avsoftware.database.user.UserDao

@Database(entities = [User::class, StockSymbolEntity::class, PortfolioTransactionEntity::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stockSymbolDao(): StockSymbolDao
    abstract fun portfolioTransactionsDao(): PortfolioTransactionsDao
}