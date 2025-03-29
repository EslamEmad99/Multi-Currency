package com.example.multicurrency.changecurrency.viewstate

import com.example.multicurrency.changecurrency.model.ExchangeRateUiModel

sealed class ExchangeRateViewState {
    data object Loading : ExchangeRateViewState()
    data class Success(val exchangeRates: List<ExchangeRateUiModel>) : ExchangeRateViewState()
    data class Error(val exception: Exception) : ExchangeRateViewState()
}