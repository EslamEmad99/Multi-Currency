package com.example.domain.usecase

import com.example.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetExchangeRatesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke() = repository.getExchangeRates()
}