package com.example.multicurrency.history.viewstate

import com.example.multicurrency.history.model.ExchangeRateHistoryUIModel

sealed class GetExchangeRateHistoryViewState {
    data object Loading : GetExchangeRateHistoryViewState()

    data class Success(val exchangeRates: List<ExchangeRateHistoryUIModel>) :
        GetExchangeRateHistoryViewState()

    data class Error(val exception: Exception) : GetExchangeRateHistoryViewState()
}