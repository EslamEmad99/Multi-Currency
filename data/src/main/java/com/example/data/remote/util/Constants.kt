package com.example.data.remote.util

object CurrencyRemoteEndPointsNames {
    const val GET_EXCHANGE_RATES = "latest"
}

object CurrencyRemoteEndPointsParameters {
    const val ACCESS_KEY = "access_key"
    const val DATE = "date"
    const val BASE = "base"
    const val SYMBOLS = "access_key"
    const val EUR = "EUR"
    private const val USD = "USD"
    private const val EGP = "EGP"
    private const val SAR = "SAR"
    private const val KWD = "KWD"
    private const val AED = "AED"
    val SUPPORTED_CURRENCIES = listOf(EUR, USD, EGP, SAR, KWD, AED).joinToString(",")
}
