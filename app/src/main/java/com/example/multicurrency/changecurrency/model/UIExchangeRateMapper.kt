package com.example.multicurrency.changecurrency.model

import com.example.domain.model.ExchangeRate

object UIExchangeRateMapper {

    private fun mapToUiModel(domainModel: ExchangeRate): ExchangeRateUiModel {
        return ExchangeRateUiModel(
            currencyName = domainModel.targetCurrency,
            rate = domainModel.rate
        )
    }

    fun mapToUiModelList(domainModels: List<ExchangeRate>): List<ExchangeRateUiModel> {
        return domainModels.map { mapToUiModel(it) }
    }
}
