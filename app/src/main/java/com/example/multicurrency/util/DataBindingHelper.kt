package com.example.multicurrency.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.multicurrency.history.model.ExchangeRateHistoryUIModel

@BindingAdapter("exchangeRateText")
fun TextView.setExchangeRateText(uiModel: ExchangeRateHistoryUIModel) {
    text =
        "${uiModel.date} ${uiModel.amount} ${uiModel.fromCurrency} = ${uiModel.convertedAmount} ${uiModel.toCurrency}"
}
