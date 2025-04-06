package com.avsoftware.data.fmp.crypto

import com.avsoftware.core.ApiError
import com.avsoftware.core.DomainResult
import com.avsoftware.domain.fmp.crypto.CryptoCurrency
import com.avsoftware.domain.fmp.crypto.GetCryptoCurrenciesUseCase
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class RetrofitGetCryptoCurrenciesUseCase @Inject constructor(
    @Named("fmpRetrofit") private val fmpRetrofit: Retrofit,
    @Named("fmpApiKey") private val apiKey: String

): GetCryptoCurrenciesUseCase {

    private val service: FinancialModelingApiGetCryptoCurrencies = fmpRetrofit.create(
        FinancialModelingApiGetCryptoCurrencies::class.java)

    override suspend fun invoke(): DomainResult<List<CryptoCurrency>, ApiError> {
        return try {
            DomainResult.Success(service.getCryptoCurrencies(apiKey))
        } catch (t: Throwable){
            Timber.d("Failed to load stocks: ${t.cause?.message}")
            DomainResult.Error(ApiError(t.message))
        }
    }
}

private interface FinancialModelingApiGetCryptoCurrencies {
    @GET("stable/cryptocurrency-list")
    suspend fun getCryptoCurrencies(@Query("apikey") apiKey: String): List<CryptoCurrency>
}