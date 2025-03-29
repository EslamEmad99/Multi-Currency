package com.example.multicurrency.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.multicurrency.databinding.ItemExchangeRateHistoryBinding
import com.example.multicurrency.history.model.ExchangeRateHistoryUIModel

class CurrencyHistoryAdapter :
    ListAdapter<ExchangeRateHistoryUIModel, CurrencyHistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExchangeRateHistoryUIModel>() {
            override fun areItemsTheSame(
                oldItem: ExchangeRateHistoryUIModel,
                newItem: ExchangeRateHistoryUIModel
            ): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(
                oldItem: ExchangeRateHistoryUIModel,
                newItem: ExchangeRateHistoryUIModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExchangeRateHistoryBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: ItemExchangeRateHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExchangeRateHistoryUIModel) {
            binding.exchangeRate = item
            binding.executePendingBindings()
        }
    }
}
