package com.avsoftware.di

import com.avsoftware.data.fmp.search.GetStockSymbolsRoomUseCase
import com.avsoftware.data.fmp.search.GetStockSymbolsUseCase
import com.avsoftware.data.fmp.search.RetrofitStockSymbolSearch
import com.avsoftware.data.fmp.search.StockSymbolSearch
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Singleton scope for app-wide use
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindStockSymbolSearch(
        retrofitStockSymbolSearch: RetrofitStockSymbolSearch
    ): StockSymbolSearch

    @Binds
    @Singleton
    abstract fun bindGetStockSymbols(
        roomUseCase: GetStockSymbolsRoomUseCase
    ): GetStockSymbolsUseCase

}