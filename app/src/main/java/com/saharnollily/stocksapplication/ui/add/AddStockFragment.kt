package com.saharnollily.stocksapplication.ui.add

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.base.viewBinding
import com.saharnollily.stocksapplication.databinding.FragmentAddStockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStockFragment : DialogFragment(R.layout.fragment_add_stock) {

    private val binding by viewBinding(FragmentAddStockBinding::bind)
    private val viewModel: AddViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserve()
        setListener()
    }

    private fun initObserve(){
        viewModel.event.observe(viewLifecycleOwner,{initEvent(it)})
    }

    private fun initEvent(stockEvent: AddViewModel.AddEvent) {
        when(stockEvent){
            is AddViewModel.AddEvent.Loading -> onLoading(stockEvent.isLoading)
            is AddViewModel.AddEvent.Success -> onSuccess(stockEvent.isSuccess)
            is AddViewModel.AddEvent.Error -> onError(stockEvent.message)
        }
    }

    private fun onSuccess(isSuccess: Boolean) {
        if(isSuccess)
            dismiss()
    }

    private fun onError(message: String) {
        binding.currencyName.error = message
    }

    private fun onLoading(loading: Boolean) {

    }

    private fun setListener(){
        binding.addCurrencyButton.setOnClickListener {
            viewModel.addCurrency(binding.currencyName.getText().toString().trim())
        }
    }

    override fun getTheme(): Int {
        return R.style.Theme_AppCompat_Dialog_Alert
    }
}