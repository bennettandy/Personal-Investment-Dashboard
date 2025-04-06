package com.avsoftware.data.stocks

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainError
import com.avsoftware.core.DomainResult
import com.avsoftware.domain.stocks.GetStocksUseCase
import com.avsoftware.domain.stocks.Stock
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named


class RetrofitGetStocksUseCase @Inject constructor(
    @Named("fmpRetrofit") private val fmpRetrofit: Retrofit,
    @Named("fmpApiKey") private val apiKey: String
): GetStocksUseCase {

    private val service: FinancialModelingApiGetStocks = fmpRetrofit.create(FinancialModelingApiGetStocks::class.java)

    override suspend operator fun invoke(): DomainResult<List<Stock>, DomainError> {
        return try {
            DomainResult.Success(service.getStockList(apiKey))
        } catch (t: Throwable){
            Timber.d("Failed to load stocks: ${t.cause?.message}")
            DomainResult.Error(ApiError(t.message))
        }
    }
}

private interface FinancialModelingApiGetStocks {
    @GET("v3/stock/list")
    suspend fun getStockList(@Query("apikey") apiKey: String): List<Stock>
}