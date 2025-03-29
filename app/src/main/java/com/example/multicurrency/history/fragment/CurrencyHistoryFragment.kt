package com.example.multicurrency.history.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.multicurrency.databinding.FragmentCurrencyHistoryBinding

class CurrencyHistoryFragment : Fragment() {

    private val args by navArgs<CurrencyHistoryFragmentArgs>()

    private lateinit var binding: FragmentCurrencyHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyHistoryBinding.inflate(inflater, container, false)
        Toast.makeText(requireContext(), "From: ${args.from}\nTo:${args.to}", Toast.LENGTH_SHORT)
            .show()
        return binding.root
    }
}