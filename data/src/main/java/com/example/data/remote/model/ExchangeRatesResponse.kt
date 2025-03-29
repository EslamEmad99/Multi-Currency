package com.example.data.remote.model

import com.squareup.moshi.Json

data class ExchangeRatesResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "base") val baseCurrency: String,
    @Json(name = "date") val date: String,
    @Json(name = "rates") val rates: Map<String, Double>
)
