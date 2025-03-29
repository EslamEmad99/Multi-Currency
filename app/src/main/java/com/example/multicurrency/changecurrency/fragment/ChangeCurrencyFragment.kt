package com.example.multicurrency.changecurrency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.multicurrency.changecurrency.viewmodel.ChangeCurrencyViewModel
import com.example.multicurrency.databinding.FragmentChangeCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeCurrencyFragment : Fragment() {

    private lateinit var binding: FragmentChangeCurrencyBinding

    private val viewModel by viewModels<ChangeCurrencyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeCurrencyBinding.inflate(inflater, container, false)
        handleClicks()
        return binding.root
    }

    private fun handleClicks() {
        binding.run {
            tvTest.setOnClickListener {
                navigateToCurrencyHistoryFragment()
            }
        }
    }

    private fun navigateToCurrencyHistoryFragment() {
        val action =
            ChangeCurrencyFragmentDirections.actionChangeCurrencyFragmentToCurrencyHistoryFragment(
                from = "USD",
                to = "EGP"
            )
        findNavController().navigate(action)
    }
}