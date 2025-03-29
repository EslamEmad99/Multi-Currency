package com.example.multicurrency.history.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.multicurrency.databinding.FragmentCurrencyHistoryBinding
import com.example.multicurrency.history.viewmodel.CurrencyHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyHistoryFragment : Fragment() {

    private val args by navArgs<CurrencyHistoryFragmentArgs>()

    private val viewModel by viewModels<CurrencyHistoryViewModel>()

    private lateinit var binding: FragmentCurrencyHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}