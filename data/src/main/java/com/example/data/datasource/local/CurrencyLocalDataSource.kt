package com.example.data.datasource.local

import com.example.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface CurrencyLocalDataSource {

    suspend fun insertExchangeRate(exchangeRate: ExchangeRate)

    suspend fun cleanupOldData()

    fun getExchangeRateHistory(from: String, to: String): Flow<List<ExchangeRate>>
}
