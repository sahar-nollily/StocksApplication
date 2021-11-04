package com.saharnollily.stocksapplication.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.base.viewBinding
import com.saharnollily.stocksapplication.databinding.FragmentHomeBinding
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.ui.SharedViewModel
import com.saharnollily.stocksapplication.utils.CreateConfirmationDialog
import com.saharnollily.stocksapplication.utils.hide
import com.saharnollily.stocksapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private var currenciesListAdapter: CurrenciesListAdapter? = null

    private val sharedViewModel by lazy { ViewModelProvider(requireActivity()).get(SharedViewModel::class.java) }


    private val navigateToDetails: (Currency) -> Unit = {
        sharedViewModel.currencey.postValue(it)
        val action = HomeFragmentDirections.actionHomeFragmentToCurrencyDetailsFragment(it.currencyId)
        findNavController().navigate(action)
    }


    private val deleteItem: (Int, Int) -> Unit = {position, id ->
        CreateConfirmationDialog.showAlert(requireContext()){
            if(it) {
                viewModel.deleteCurrency(id)
                viewModel.currencyList.removeAt(position)
                currenciesListAdapter?.notifyItemRemoved(position)
            }
        }
    }

    private val updateItem: (String, Int) -> Unit = {name, id ->
        currenciesListAdapter?.notifyDataSetChanged()
        viewModel.updateCurrencyName(id, name)

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
        viewModel.currencyList.clear()
        viewModel.currencyList.addAll(currencies)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        if(viewModel.currencyList.isNullOrEmpty()){
            binding.noDataTextView.show()
            binding.recyclerView.hide()
        }else{
            binding.noDataTextView.hide()
            binding.recyclerView.show()
            currenciesListAdapter = CurrenciesListAdapter(
                viewModel.currencyList,
                navigateToDetails,
                deleteItem,
                updateItem
            )
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = currenciesListAdapter
            }

        }
    }
}