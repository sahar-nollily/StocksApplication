package com.saharnollily.stocksapplication.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.base.viewBinding
import com.saharnollily.stocksapplication.databinding.FragmentHomeBinding
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.utils.hide
import com.saharnollily.stocksapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val navigateToDetails: (Int) -> Unit = {
        val action = HomeFragmentDirections.actionHomeFragmentToCurrencyDetailsFragment(it)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserve()
        setListener()


    }

    private fun initObserve(){
        viewModel.event.observe(viewLifecycleOwner,{initEvent(it)})
        viewModel.getAllCurrency.observe(viewLifecycleOwner,{fillGui(it)})
    }

    private fun initEvent(stockEvent: HomeViewModel.StockEvent) {
        when(stockEvent){
            is HomeViewModel.StockEvent.Loading -> onLoading(stockEvent.isLoading)
            is HomeViewModel.StockEvent.Success -> onSuccess(stockEvent.data)
            is HomeViewModel.StockEvent.Error -> onError(stockEvent.message)
        }
    }

    private fun onSuccess(data: Currency?) {
    }

    private fun onError(message: String) {

    }

    private fun onLoading(loading: Boolean) {

    }

    private fun setListener(){
        binding.addStockButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddStockFragment()
            findNavController().navigate(action)
        }
    }

    private fun fillGui(currencies: List<Currency>){
        if(currencies.isNullOrEmpty()){
            binding.noDataTextView.show()
            binding.recyclerView.hide()
        }else{
            binding.noDataTextView.hide()
            binding.recyclerView.show()
            binding.recyclerView.apply {
                adapter = CurrenciesListAdapter(currencies, navigateToDetails)
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }
}