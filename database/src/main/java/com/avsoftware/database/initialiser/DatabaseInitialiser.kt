package com.avsoftware.database.initialiser

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.avsoftware.database.AppDatabase
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.database.stock.StockSymbolEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                        prePopulateDatabase(getDatabase(context).stockSymbolDao())
                    }
                }
            })
            .build()
    }

    private suspend fun prePopulateDatabase(dao: StockSymbolDao) {
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
}