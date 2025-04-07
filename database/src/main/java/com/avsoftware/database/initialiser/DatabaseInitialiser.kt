package com.avsoftware.database.initialiser

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.avsoftware.database.AppDatabase
import com.avsoftware.database.stock.PortfolioTransactionEntity
import com.avsoftware.database.stock.PortfolioTransactionsDao
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.database.stock.StockSymbolEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

object DatabaseInitializer {

    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Run initial data population on a background thread
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = getDatabase(context)
                        prePopulateDatabaseStockSymbols(database.stockSymbolDao())
                        prePopulateDatabaseTransactions(database.portfolioTransactionsDao())
                    }
                }
            })
            .fallbackToDestructiveMigration() // use during development, drop and recreate when version changes
            .build()
    }

    // Reset the database during development
    // Debug method to reset database
    fun resetDatabase(context: Context) {
        context.deleteDatabase("app_database") // Deletes the database file
    }

    private  fun prePopulateDatabaseStockSymbols(dao: StockSymbolDao) {
        val initialTransactions = listOf(
            StockSymbolEntity(
                symbol = "TSLA",
                name = "Tesla",
                currency = "USD",
                exchangeFullName = "NASDAQ",
                exchange = "NASDAQ"
            )
        )
        dao.insertAll(initialTransactions)
    }

    private fun prePopulateDatabaseTransactions(dao: PortfolioTransactionsDao){
        val transactions = listOf(
            PortfolioTransactionEntity(
                transactionDate = OffsetDateTime.now(),
                assetKey = "TSLA",
            )
        )
        dao.insertAll(transactions)
    }
}