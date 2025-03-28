package com.example.data.di

import com.example.data.datasource.local.CurrencyLocalDataSource
import com.example.data.datasource.remote.CurrencyRemoteDataSource
import com.example.data.local.dao.ExchangeRateDao
import com.example.data.local.datasource.CurrencyLocalDataSourceImpl
import com.example.data.remote.datasource.CurrencyRemoteDataSourceImpl
import com.example.data.remote.endpoints.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCurrencyRemoteDataSource(apiService: CurrencyApiService): CurrencyRemoteDataSource {
        return CurrencyRemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCurrencyLocalDataSource(exchangeRateDao: ExchangeRateDao): CurrencyLocalDataSource {
        return CurrencyLocalDataSourceImpl(exchangeRateDao)
    }
}
