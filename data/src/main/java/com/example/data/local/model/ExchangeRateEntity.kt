package com.example.data.local.model

import androidx.room.Entity
import com.example.data.local.util.Constants.HISTORICAL_EXCHANGES_TABLE_NAME

@Entity(tableName = HISTORICAL_EXCHANGES_TABLE_NAME, primaryKeys = ["date", "baseCurrency", "targetCurrency"])
data class ExchangeRateEntity(
    val date: String,
    val baseCurrency: String,
    val targetCurrency: String,
    val rate: Double
)
