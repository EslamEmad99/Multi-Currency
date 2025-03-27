package com.example.data.remote.endpoints

import com.example.data.remote.model.ExchangeRatesResponse
import com.example.data.remote.util.CurrencyRemoteEndPointsNames.GET_EXCHANGE_RATES
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.ACCESS_KEY
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.BASE
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.DATE
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.EUR
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.SUPPORTED_CURRENCIES
import com.example.data.remote.util.CurrencyRemoteEndPointsParameters.SYMBOLS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApiService {

    @GET(GET_EXCHANGE_RATES)
    suspend fun getExchangeRates(
        @Query(ACCESS_KEY) accessKey: String,
        @Query(BASE) base: String = EUR,
        @Query(SYMBOLS) symbols: String = SUPPORTED_CURRENCIES
    ): Response<ExchangeRatesResponse>

    @GET("{$DATE}")
    suspend fun getHistoricalRates(
        @Path(DATE) date: String,
        @Query(ACCESS_KEY) accessKey: String,
        @Query(BASE) base: String = EUR,
        @Query(SYMBOLS) symbols: String = SUPPORTED_CURRENCIES
    ): Response<ExchangeRatesResponse>
}
