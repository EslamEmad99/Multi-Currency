package com.example.data.repository

import com.example.data.datasource.local.CurrencyLocalDataSource
import com.example.data.datasource.remote.CurrencyRemoteDataSource
import com.example.data.util.Constants.CURRENCY_APP_DATE_FORMAT_PATTERN
import com.example.domain.model.ExchangeRate
import com.example.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: CurrencyRemoteDataSource,
    private val localDataSource: CurrencyLocalDataSource
) : CurrencyRepository {

    override suspend fun getExchangeRates(): Result<List<ExchangeRate>> {
        return remoteDataSource.getExchangeRates()
    }

    override fun getExchangeRateHistory(from: String, to: String) = flow {
        val cachedData = localDataSource.getExchangeRateHistory(from, to).firstOrNull().orEmpty()

        val missingDates = getMissingTimestamps(cachedData)

        missingDates.forEach { timestamp ->
            val result = remoteDataSource.getExchangeRateHistoryFromRemote(timestamp, from, to)

            if (result.isSuccess) {
                result.getOrNull()?.let { exchangeRate ->
                    localDataSource.insertExchangeRate(exchangeRate)
                }
            }
        }

        localDataSource.getExchangeRateHistory(from, to)
            .map { Result.success(it) }
            .collect { emit(it) }
    }.catch { e -> emit(Result.failure(e)) }


    private fun getMissingTimestamps(cachedData: List<ExchangeRate>): List<Long> {
        val today = Calendar.getInstance()

        val expectedTimestamps = (1..4).map { daysAgo ->
            (today.clone() as Calendar).apply {
                add(
                    Calendar.DAY_OF_YEAR,
                    -daysAgo
                )
            }.timeInMillis / 1000
        }

        val existingTimestamps = cachedData.map { it.date.toTimestamp() }

        return expectedTimestamps.filter { it !in existingTimestamps }
    }

    private fun String.toTimestamp(): Long {
        val sdf = SimpleDateFormat(CURRENCY_APP_DATE_FORMAT_PATTERN, Locale.getDefault())
        return sdf.parse(this)?.time?.div(1000) ?: 0L
    }

    override suspend fun cleanupOldData() {
        localDataSource.cleanupOldData()
    }
}
