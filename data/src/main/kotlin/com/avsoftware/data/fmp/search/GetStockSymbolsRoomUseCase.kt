package com.avsoftware.data.fmp.search

import com.avsoftware.model.StockSymbol
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.domain.fmp.search.GetStockSymbolsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStockSymbolsRoomUseCase @Inject constructor(
    private val stockSymbolDao: StockSymbolDao
): GetStockSymbolsUseCase {
    override fun getSelectedStockSymbols(): Flow<List<StockSymbol>> =
        stockSymbolDao.getAll().map { stockSymbols ->
            stockSymbols.map { stockSymbolEntity ->
                // Domain Object
                StockSymbol(
                    symbol = stockSymbolEntity.symbol,
                    name = stockSymbolEntity.name,
                    currency = stockSymbolEntity.currency,
                    exchangeFullName = stockSymbolEntity.exchangeFullName,
                    exchange = stockSymbolEntity.exchange
                )
            }
        }
}
