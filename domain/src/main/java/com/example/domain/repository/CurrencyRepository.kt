package com.example.domain.repository

import com.example.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getExchangeRates(): Result<List<ExchangeRate>>

    suspend fun cleanupOldData()

    fun getExchangeRateHistory(from: String, to: String): Flow<Result<List<ExchangeRate>>>
}