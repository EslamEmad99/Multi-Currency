package com.example.multicurrency.changecurrency.viewstate

sealed class ConvertCurrencyViewState {
    data object Idel: ConvertCurrencyViewState()
    data class Success(val value: String) : ConvertCurrencyViewState()
    data class Error(val exception: Exception) : ConvertCurrencyViewState()
}