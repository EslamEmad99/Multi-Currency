package com.example.data.remote.datasource

import com.example.data.datasource.remote.CurrencyRemoteDataSource
import com.example.data.remote.endpoints.CurrencyApiService
import com.example.data.remote.util.ApiExceptions
import com.example.data.remote.util.RemoteExchangeRateMapper
import com.example.data.remote.util.safeApiCall
import com.example.data.util.Secrets
import com.example.domain.model.ExchangeRate
import javax.inject.Inject

class CurrencyRemoteDataSourceImpl @Inject constructor(
    private val apiService: CurrencyApiService
) : CurrencyRemoteDataSource {

    override suspend fun getExchangeRates(): Result<List<ExchangeRate>> {
        return safeApiCall {
            apiService.getExchangeRates(accessKey = Secrets.getApiKey())
        }.mapCatching { responseDto ->
            if (!responseDto.success) throw ApiExceptions.EmptyResponseException

            RemoteExchangeRateMapper.mapFromDto(responseDto)
        }
    }

    override suspend fun getExchangeRateHistoryFromRemote(
        date: String,
        from: String,
        to: String
    ): Result<ExchangeRate> {

        return safeApiCall {
            apiService.getHistoricalRates(
                date = date,
                accessKey = Secrets.getApiKey()
            )
        }.mapCatching { responseDto ->
            if (!responseDto.success) return Result.failure(ApiExceptions.EmptyResponseException)

            RemoteExchangeRateMapper.mapFromDto(responseDto, from, to)
        }
    }
}
