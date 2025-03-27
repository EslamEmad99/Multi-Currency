package com.example.data.remote.model

import com.google.gson.annotations.SerializedName

data class ExchangeRatesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("base") val baseCurrency: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
)
