package com.example.multicurrency.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.CleanupDatabaseUseCase
import com.example.domain.usecase.GetExchangeRateHistoryUseCase
import com.example.multicurrency.history.model.UIExchangeRateHistoryMapper
import com.example.multicurrency.history.viewstate.GetExchangeRateHistoryViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyHistoryViewModel @Inject constructor(
    private val getExchangeRateHistoryUseCase: GetExchangeRateHistoryUseCase,
    private val cleanupDatabaseUseCase: CleanupDatabaseUseCase
) : ViewModel() {

    private val _historicalExchangeRatesState =
        MutableStateFlow<GetExchangeRateHistoryViewState>(GetExchangeRateHistoryViewState.Loading)
    val historicalExchangeRatesState = _historicalExchangeRatesState.asStateFlow()

    fun getHistoricalExchangeRates(
        amount: Double,
        from: String,
        to: String
    ) {
        viewModelScope.launch {
            getExchangeRateHistoryUseCase(from = from, to = to)
                .catch { exception ->
                    _historicalExchangeRatesState.value =
                        GetExchangeRateHistoryViewState.Error(exception as Exception)
                }
                .collect { result ->
                    result.onSuccess { exchangeRates ->
                        val uiModels = UIExchangeRateHistoryMapper.mapToUiModelList(
                            domainModels = exchangeRates,
                            amount = amount,
                            fromCurrency = from,
                            toCurrency = to
                        )
                        _historicalExchangeRatesState.value =
                            GetExchangeRateHistoryViewState.Success(uiModels)
                    }.onFailure { exception ->
                        _historicalExchangeRatesState.value =
                            GetExchangeRateHistoryViewState.Error(exception as Exception)
                    }
                }
        }
    }

    fun cleanupDatabase() {
        viewModelScope.launch { cleanupDatabaseUseCase() }
    }
}