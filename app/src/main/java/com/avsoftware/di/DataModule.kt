package com.avsoftware.di

import com.avsoftware.data.fmp.crypto.RetrofitGetCryptoCurrenciesUseCase
import com.avsoftware.data.fmp.search.GetStockSymbolsRoomUseCase
import com.avsoftware.domain.fmp.search.GetStockSymbolsUseCase
import com.avsoftware.data.fmp.search.RetrofitStockSymbolSearch
import com.avsoftware.data.stocks.RetrofitGetStocksUseCase
import com.avsoftware.domain.fmp.crypto.GetCryptoCurrenciesUseCase
import com.avsoftware.domain.fmp.search.StockSymbolSearch
import com.avsoftware.domain.stocks.GetStocksUseCase
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

    @Binds
    @Singleton
    abstract fun bindGetStocksUseCase(
        retrofitGetStocksUseCase: RetrofitGetStocksUseCase
    ): GetStocksUseCase

    @Binds
    @Singleton
    abstract fun bindGetCryptoCurrenciesUseCase(
        retrofitGetCryptoCurrenciesUseCase: RetrofitGetCryptoCurrenciesUseCase
    ): GetCryptoCurrenciesUseCase
}