package com.example.data.local.datasource

import com.example.data.datasource.local.CurrencyLocalDataSource
import com.example.data.local.dao.ExchangeRateDao
import com.example.data.local.util.LocalExchangeRateMapper
import com.example.data.util.Constants.CURRENCY_APP_DATE_FORMAT_PATTERN
import com.example.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class CurrencyLocalDataSourceImpl @Inject constructor(
    private val exchangeRateDao: ExchangeRateDao
) : CurrencyLocalDataSource {

    override suspend fun insertExchangeRate(exchangeRate: ExchangeRate) {
        exchangeRateDao.insertExchangeRate(LocalExchangeRateMapper.mapToEntity(exchangeRate))
    }

    override suspend fun cleanupOldData() {
        exchangeRateDao.cleanupOldData(minDate = getMinDate())
    }

    private fun getMinDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -4)
        val sdf = SimpleDateFormat(CURRENCY_APP_DATE_FORMAT_PATTERN, Locale.getDefault())
        return sdf.format(calendar.time)
    }

    override fun getExchangeRateHistory(from: String, to: String): Flow<List<ExchangeRate>> {
        return exchangeRateDao.getExchangeRateHistory(from, to)
            .map { entities -> entities.map(LocalExchangeRateMapper::mapToDomain) }
    }
}
