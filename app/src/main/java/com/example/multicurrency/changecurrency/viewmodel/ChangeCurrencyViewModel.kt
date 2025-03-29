package com.example.multicurrency.changecurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ConvertCurrencyUseCase
import com.example.domain.usecase.GetExchangeRatesUseCase
import com.example.multicurrency.changecurrency.model.ExchangeRateUiModel
import com.example.multicurrency.changecurrency.model.UIExchangeRateMapper
import com.example.multicurrency.changecurrency.viewstate.ConvertCurrencyViewState
import com.example.multicurrency.changecurrency.viewstate.ExchangeRateViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeCurrencyViewModel @Inject constructor(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    var fromCurrency: ExchangeRateUiModel? = null
    var toCurrency: ExchangeRateUiModel? = null

    private val _exchangeRatesState =
        MutableStateFlow<ExchangeRateViewState>(ExchangeRateViewState.Loading)
    val exchangeRatesState = _exchangeRatesState.asStateFlow()

    private val _convertedValue =
        MutableStateFlow<ConvertCurrencyViewState>(ConvertCurrencyViewState.Idel)
    val convertedValue = _convertedValue.asStateFlow()

    fun getExchangeRates() {
        viewModelScope.launch {
            _exchangeRatesState.value = ExchangeRateViewState.Loading
            val result = getExchangeRatesUseCase()
            result.onSuccess { exchangeRates ->
                val uiModels = UIExchangeRateMapper.mapToUiModelList(exchangeRates)
                _exchangeRatesState.value = ExchangeRateViewState.Success(uiModels)
            }.onFailure { exception ->
                _exchangeRatesState.value = ExchangeRateViewState.Error(exception as Exception)
            }
        }
    }

    fun convertCurrency(amount: Double) {
        if (fromCurrency == null || toCurrency == null) {
            return
        } else {
            viewModelScope.launch {
                val convertedAmount = convertCurrencyUseCase(
                    fromRate = fromCurrency!!.rate,
                    toRate = toCurrency!!.rate,
                    amount = amount
                )
                convertedAmount.onSuccess {
                    _convertedValue.value = ConvertCurrencyViewState.Success("$it")
                }.onFailure {
                    _convertedValue.value = ConvertCurrencyViewState.Error(it as Exception)
                }
            }
        }
    }
}
