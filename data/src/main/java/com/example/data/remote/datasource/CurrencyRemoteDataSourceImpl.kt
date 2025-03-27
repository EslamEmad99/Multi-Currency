package com.example.data.remote.datasource

import com.example.data.datasource.remote.CurrencyRemoteDataSource
import com.example.data.remote.endpoints.CurrencyApiService
import com.example.data.remote.util.ApiExceptions
import com.example.data.remote.util.RemoteExchangeRateMapper
import com.example.data.remote.util.safeApiCall
import com.example.data.util.Constants.CURRENCY_APP_DATE_FORMAT_PATTERN
import com.example.domain.model.ExchangeRate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val apiService: CurrencyApiService,
    private val accessKey: String
) : CurrencyRemoteDataSource {

    override suspend fun getExchangeRates(): Result<List<ExchangeRate>> {
        return safeApiCall {
            apiService.getExchangeRates(accessKey = accessKey)
        }.mapCatching { responseDto ->
            if (!responseDto.success) throw ApiExceptions.EmptyResponseException

            RemoteExchangeRateMapper.mapFromDto(responseDto)
        }
    }

    override suspend fun getExchangeRateHistoryFromRemote(
        todayTimeStamp: Long,
        from: String,
        to: String
    ): Result<ExchangeRate> {
        val date = convertTimestampToDate(todayTimeStamp)

        return safeApiCall {
            apiService.getHistoricalRates(date, from, to, accessKey)
        }.mapCatching { responseDto ->
            if (!responseDto.success) return Result.failure(ApiExceptions.EmptyResponseException)

            RemoteExchangeRateMapper.mapFromDto(responseDto, from, to)
        }
    }

    private fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat(CURRENCY_APP_DATE_FORMAT_PATTERN, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date(timestamp * 1000))
    }
}
