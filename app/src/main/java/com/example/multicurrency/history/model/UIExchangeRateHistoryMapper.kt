package com.example.multicurrency.history.model

import com.example.domain.model.ExchangeRate

object UIExchangeRateHistoryMapper {

    private fun mapToUiModel(
        domainModel: ExchangeRate,
        amount: Double,
        fromCurrency: String,
        toCurrency: String
    ) = ExchangeRateHistoryUIModel(
        date = domainModel.date,
        amount = amount,
        fromCurrency = fromCurrency,
        toCurrency = toCurrency,
        convertedAmount = amount * domainModel.rate
    )

    fun mapToUiModelList(
        domainModels: List<ExchangeRate>,
        amount: Double,
        fromCurrency: String,
        toCurrency: String
    ): List<ExchangeRateHistoryUIModel> {
        return domainModels.map {
            mapToUiModel(
                domainModel = it,
                amount = amount,
                fromCurrency = fromCurrency,
                toCurrency = toCurrency
            )
        }
    }
}
