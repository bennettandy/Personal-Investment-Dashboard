package com.avsoftware.data.fmp.search

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainResult
import com.avsoftware.model.StockSymbol
import com.avsoftware.database.stock.StockSymbolDao
import com.avsoftware.database.stock.StockSymbolEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class RetrofitStockSymbolSearch @Inject constructor(
    @Named("fmpRetrofit") retrofit: Retrofit,
    @Named("fmpApiKey") private val apiKey: String,
    private val stockSymbolDao: StockSymbolDao
) : StockSymbolSearch {
    private val api: FinancialModelingApi = retrofit.create(FinancialModelingApi::class.java)

    override suspend fun searchSymbol(searchQuery: String): DomainResult<List<StockSymbol>, ApiError> =
        withContext(Dispatchers.IO) {

            val cachedList: List<StockSymbolEntity> = stockSymbolDao.findByName(searchQuery)

            Timber.d("Got ${cachedList.size} cached symbols")

            try {
                val response = api.searchSymbol(searchQuery, apiKey = apiKey)
                if (response.isSuccessful) {
                    val stocks = response.body() ?: return@withContext DomainResult.Error(
                        ApiError("Empty response body")
                    )
                    DomainResult.Success(stocks.map {
                        stockSymbolDao.upsert(it.toEntity())
                        it.toDomain()
                    })
                } else {
                    DomainResult.Error(ApiError("API error: ${response.code()}"))
                }
            } catch (e: Exception) {
                DomainResult.Error(ApiError("Network error: ${e.message}"))
            }
        }
}

private interface FinancialModelingApi {
    @GET("stable/search-symbol")
    suspend fun searchSymbol(
        @Query("query") query: String,
        @Query("apikey") apiKey: String
    ): Response<List<StockSearchResponse>>
}