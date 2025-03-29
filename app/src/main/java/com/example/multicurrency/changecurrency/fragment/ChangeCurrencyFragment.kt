package com.example.multicurrency.changecurrency.fragment

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.exceptions.ConvertCurrencyExceptions
import com.example.multicurrency.R
import com.example.multicurrency.changecurrency.model.ExchangeRateUiModel
import com.example.multicurrency.changecurrency.viewmodel.ChangeCurrencyViewModel
import com.example.multicurrency.changecurrency.viewstate.ConvertCurrencyViewState
import com.example.multicurrency.changecurrency.viewstate.ExchangeRateViewState
import com.example.multicurrency.databinding.FragmentChangeCurrencyBinding
import com.example.multicurrency.util.ProgressUtil
import com.example.multicurrency.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangeCurrencyFragment : Fragment() {

    private lateinit var binding: FragmentChangeCurrencyBinding

    private val viewModel by viewModels<ChangeCurrencyViewModel>()

    private val loadingDialog by lazy {
        ProgressUtil(requireContext())
    }

    private lateinit var fromCurrencyAdapter: ArrayAdapter<String>
    private lateinit var toCurrencyAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExchangeRates()
    }

    private fun getExchangeRates() {
        viewModel.getExchangeRates()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeCurrencyBinding.inflate(inflater, container, false)
        initGetExchangeRatesObserver()
        initConvertCurrencyObserver()
        initInputValueListener()
        handleClicks()
        return binding.root
    }

    private fun initGetExchangeRatesObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exchangeRatesState.collectLatest { state ->
                    when (state) {
                        is ExchangeRateViewState.Loading -> {
                            handleLoading(showLoading = true)
                        }

                        is ExchangeRateViewState.Success -> {
                            handleLoading(showLoading = false)
                            setupSpinners(state.exchangeRates)
                        }

                        is ExchangeRateViewState.Error -> {
                            handleLoading(showLoading = false)
                            handleException(exception = state.exception)
                        }
                    }
                }
            }
        }
    }

    private fun handleLoading(showLoading: Boolean) {
        loadingDialog.statusProgress(showLoading)
    }

    private fun initConvertCurrencyObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.convertedValue.collectLatest { state ->
                when (state) {
                    is ConvertCurrencyViewState.Success -> {
                        binding.convertedAmountEditText.setText(state.value)
                    }

                    is ConvertCurrencyViewState.Error -> handleException(exception = state.exception)

                    is ConvertCurrencyViewState.Idel -> {}
                }
            }
        }
    }

    private fun setupSpinners(exchangeRates: List<ExchangeRateUiModel>) {
        val currencyNames = exchangeRates.map { it.currencyName }

        fromCurrencyAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            currencyNames
        )
        toCurrencyAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            currencyNames
        )

        binding.fromCurrencySpinner.setAdapter(fromCurrencyAdapter)
        binding.toCurrencySpinner.setAdapter(toCurrencyAdapter)

        binding.fromCurrencySpinner.setOnItemClickListener { _, _, position, _ ->
            viewModel.fromCurrency = exchangeRates[position]
            convertCurrency()
        }

        binding.toCurrencySpinner.setOnItemClickListener { _, _, position, _ ->
            viewModel.toCurrency = exchangeRates[position]
            convertCurrency()
        }
    }

    private fun handleException(exception: Exception) {
        when (exception) {
            is ConvertCurrencyExceptions.InvalidExchangeRateException, is ConvertCurrencyExceptions.InvalidAmountException -> {
                binding.convertedAmountEditText.text = null
            }

        }
    }

    private fun initInputValueListener() {
        binding.amountEditText.run {
            doAfterTextChanged {
                convertCurrency()
            }
            filters = arrayOf(InputFilter { source, _, _, dest, _, _ ->
                val input = dest.toString() + source.toString()
                val regex = Regex("^[0-9]{0,5}(\\.[0-9]{0,2})?$")

                if (input.matches(regex)) {
                    return@InputFilter source
                } else {
                    return@InputFilter ""
                }
            })
        }
    }

    private fun handleClicks() {
        binding.run {
            swapCurrencies.setOnClickListener {
                val temp = viewModel.fromCurrency
                viewModel.fromCurrency = viewModel.toCurrency
                viewModel.toCurrency = temp

                binding.fromCurrencySpinner.setText(viewModel.fromCurrency?.currencyName, false)
                binding.toCurrencySpinner.setText(viewModel.toCurrency?.currencyName, false)

                convertCurrency()
            }

            viewHistoryText.setOnClickListener {
                navigateToCurrencyHistoryFragment()
            }
        }
    }

    private fun convertCurrency() {
        val amount = binding.amountEditText.text.toString().toDoubleOrNull()
        viewModel.convertCurrency(amount ?: 0.0)
    }

    private fun navigateToCurrencyHistoryFragment() {
        val from = viewModel.fromCurrency
        val to = viewModel.toCurrency
        val amount = binding.amountEditText.text.toString().toFloatOrNull()

        if (from == null || to == null) {
            showToast(getString(R.string.please_select_from_to_currencies_first))
        } else {
            val action =
                ChangeCurrencyFragmentDirections.actionChangeCurrencyFragmentToCurrencyHistoryFragment(
                    from = from.currencyName,
                    to = to.currencyName,
                    amount = amount ?: 1f
                )
            findNavController().navigate(action)
        }
    }
}