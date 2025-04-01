package com.avsoftware.di

import com.avsoftware.dashboard.BuildConfig
import retrofit2.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Named("fmpApiKey")
    fun provideFmpApiKey(): String = BuildConfig.FMP_API_KEY

    @Provides
    @Singleton
    @Named("fmpRetrofit")
    fun provideFmpRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FMP_BASE_URL)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}