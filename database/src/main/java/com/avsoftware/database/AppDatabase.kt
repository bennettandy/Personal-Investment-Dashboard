package com.avsoftware.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.database.stock.StockSymbolEntity
import com.avsoftware.database.user.User
import com.avsoftware.database.user.UserDao

@Database(entities = [User::class, StockSymbolEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stockSymbolDao(): StockSymbolDao
}