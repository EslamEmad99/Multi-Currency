package com.example.data.datasource.remote

import com.example.domain.model.ExchangeRate

interface CurrencyRemoteDataSource {

    suspend fun getExchangeRates(): Result<List<ExchangeRate>>

    suspend fun getExchangeRateHistoryFromRemote(
        todayTimeStamp: Long,
        from: String,
        to: String
    ): Result<ExchangeRate>
}