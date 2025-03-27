package com.example.domain.exceptions

sealed class ConvertCurrencyExceptions : Exception() {
    data object InvalidExchangeRateException : ConvertCurrencyExceptions()
    data object InvalidAmountException : ConvertCurrencyExceptions()
}