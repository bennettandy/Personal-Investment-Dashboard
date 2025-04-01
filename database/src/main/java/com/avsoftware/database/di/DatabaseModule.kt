package com.avsoftware.database.di

import android.content.Context
import androidx.room.Room
import com.avsoftware.database.AppDatabase
import com.avsoftware.database.stock.StockSymbolDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }

    @Provides
    fun provideStockSymbolDao(appDatabase: AppDatabase): StockSymbolDao {
        return appDatabase.stockSymbolDao()
    }


}