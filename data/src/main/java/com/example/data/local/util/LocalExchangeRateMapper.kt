package com.example.data.local.util

import com.example.data.local.model.ExchangeRateEntity
import com.example.domain.model.ExchangeRate

object LocalExchangeRateMapper {

    fun mapToDomain(entity: ExchangeRateEntity) = ExchangeRate(
        baseCurrency = entity.baseCurrency,
        targetCurrency = entity.targetCurrency,
        rate = entity.rate,
        date = entity.date
    )

    fun mapToEntity(domain: ExchangeRate) = ExchangeRateEntity(
        baseCurrency = domain.baseCurrency,
        targetCurrency = domain.targetCurrency,
        rate = domain.rate,
        date = domain.date
    )
}
