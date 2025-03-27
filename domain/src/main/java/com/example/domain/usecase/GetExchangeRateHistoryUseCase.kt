package com.example.domain.usecase

import com.example.domain.repository.CurrencyRepository

class GetExchangeRateHistoryUseCase(
    private val repository: CurrencyRepository
) {

    operator fun invoke(from: String, to: String) =
        repository.getExchangeRateHistory(from = from, to = to)
}