package com.saharnollily.stocksapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.base.viewBinding
import com.saharnollily.stocksapplication.databinding.FragmentCurrencyDetailsBinding


class CurrencyDetailsFragment : Fragment(R.layout.fragment_currency_details) {

    private val binding by viewBinding(FragmentCurrencyDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillGui()
    }

    private fun fillGui(){

    }
}