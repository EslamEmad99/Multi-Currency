package com.example.multicurrency.history.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.multicurrency.databinding.FragmentCurrencyHistoryBinding
import com.example.multicurrency.history.adapter.CurrencyHistoryAdapter
import com.example.multicurrency.history.model.ExchangeRateHistoryUIModel
import com.example.multicurrency.history.viewmodel.CurrencyHistoryViewModel
import com.example.multicurrency.history.viewstate.GetExchangeRateHistoryViewState
import com.example.multicurrency.util.ProgressUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyHistoryFragment : Fragment() {

    private val args by navArgs<CurrencyHistoryFragmentArgs>()

    private val viewModel by viewModels<CurrencyHistoryViewModel>()

    private lateinit var binding: FragmentCurrencyHistoryBinding

    private val loadingDialog by lazy {
        ProgressUtil(requireContext())
    }

    private val adapter by lazy { CurrencyHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cleanupDatabase()
    }

    private fun cleanupDatabase() {
        viewModel.cleanupDatabase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyHistoryBinding.inflate(inflater, container, false)
        getHistoricalExchangeRates()
        observeHistoricalExchangeRates()
        return binding.root
    }

    private fun getHistoricalExchangeRates() {
        viewModel.getHistoricalExchangeRates(
            amount = args.amount.toDouble(),
            from = args.from,
            to = args.to
        )
    }

    private fun observeHistoricalExchangeRates() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.historicalExchangeRatesState.collectLatest { state ->
                    when (state) {
                        is GetExchangeRateHistoryViewState.Loading -> handleLoading(true)

                        is GetExchangeRateHistoryViewState.Success -> {
                            handleLoading(false)
                            initHistoryRV(state.exchangeRates)
                        }

                        is GetExchangeRateHistoryViewState.Error -> {
                            handleLoading(false)
                            handleException(state.exception)
                        }
                    }
                }
            }
        }
    }

    private fun handleLoading(showLoading: Boolean) {
        loadingDialog.statusProgress(showLoading)
    }

    private fun handleException(exception: Exception) {
        when (exception) {

        }
    }

    private fun initHistoryRV(exchangeRates: List<ExchangeRateHistoryUIModel>) {
        adapter.submitList(exchangeRates)
        binding.recyclerView.adapter = adapter
    }
}