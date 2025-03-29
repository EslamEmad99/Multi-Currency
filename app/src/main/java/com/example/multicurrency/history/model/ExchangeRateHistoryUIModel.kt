package com.example.multicurrency.history.model

data class ExchangeRateHistoryUIModel(
    val date: String,
    val amount: Double,
    val fromCurrency: String,
    val toCurrency: String,
    val convertedAmount: Double
)
