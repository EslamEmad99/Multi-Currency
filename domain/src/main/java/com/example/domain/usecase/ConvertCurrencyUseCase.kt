package com.example.domain.usecase

import com.example.domain.exceptions.ConvertCurrencyExceptions

class ConvertCurrencyUseCase {

    operator fun invoke(fromRate: Double, toRate: Double, amount: Double) =
        if (fromRate <= 0 || toRate <= 0) {
            Result.failure(ConvertCurrencyExceptions.InvalidExchangeRateException)
        } else if (amount < 1) {
            Result.failure(ConvertCurrencyExceptions.InvalidAmountException)
        } else {
            Result.success((amount / fromRate) * toRate)
        }
}