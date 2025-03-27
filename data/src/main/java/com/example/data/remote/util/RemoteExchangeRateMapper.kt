package com.example.data.remote.util

import com.example.data.remote.model.ExchangeRatesResponse
import com.example.domain.model.ExchangeRate

object RemoteExchangeRateMapper {
    fun mapFromDto(response: ExchangeRatesResponse): List<ExchangeRate> {
        return response.rates.map { (currency, rate) ->
            ExchangeRate(
                baseCurrency = response.baseCurrency,
                targetCurrency = currency,
                rate = rate,
                date = response.date
            )
        }
    }

    fun mapFromDto(response: ExchangeRatesResponse, from: String, to: String): ExchangeRate {
        val rate = response.rates[to] ?: throw ApiExceptions.EmptyResponseException
        return ExchangeRate(
            baseCurrency = from,
            targetCurrency = to,
            rate = rate,
            date = response.date
        )
    }
}
