package com.saharnollily.stocksapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.base.viewBinding
import com.saharnollily.stocksapplication.databinding.FragmentCurrencyDetailsBinding
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import com.saharnollily.stocksapplication.ui.SharedViewModel
import com.saharnollily.stocksapplication.utils.CreateConfirmationDialog
import com.saharnollily.stocksapplication.utils.hide
import com.saharnollily.stocksapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailsFragment : Fragment(R.layout.fragment_currency_details) {

    private val binding by viewBinding(FragmentCurrencyDetailsBinding::bind)
    private val viewModel : CurrencyDetailsViewModel by viewModels()
    private val sharedViewModel by lazy { ViewModelProvider(requireActivity()).get(SharedViewModel::class.java) }
    private val args by navArgs<CurrencyDetailsFragmentArgs>()

    private val addNewStock : (Float,Float,Float) -> Unit = { stockQuantity, purchasingPrice , totalAmount ->
        viewModel.addCurrencyInformation(args.currencyId, stockQuantity,purchasingPrice, totalAmount)
    }

    private val deleteStock: (Stock, Int) -> Unit = {stock, position ->
        CreateConfirmationDialog.showAlert(requireContext()){
            if(it) {
                viewModel.deleteStock(stock.stockId,stock.stockQuantity,stock.totalAmount)
                viewModel.data.removeAt(position)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSum(args.currencyId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        setListener()

    }

    private fun initObserver(){
        sharedViewModel.currencey.observe(viewLifecycleOwner,{
            viewModel.currencey = it
            fillGUI(it)

        })
        viewModel.event.observe(viewLifecycleOwner,{ initEvent(it)})

        sharedViewModel.currencey.observe(viewLifecycleOwner,{fillGUI(it)})

        if(viewModel.data.isNullOrEmpty())
            viewModel.getCurrencyInformation(args.currencyId)
        else
            stocksRecyclerView()

        viewModel.getPurchasingPriceSum.observe(viewLifecycleOwner,{
            if(viewModel.data.size != 0){
                binding.averagePrice.show()
                val round = it / viewModel.data.size
                binding.averagePrice.text ="${requireContext().getString(R.string.average_price)} ${round}"
            }
        })
    }


    private fun initEvent(event: CurrencyDetailsViewModel.StockEvent) {
        when(event){
            is CurrencyDetailsViewModel.StockEvent.Success -> onSuccess(event.data)
            is CurrencyDetailsViewModel.StockEvent.Error -> onError(event.message,event.type)
            is CurrencyDetailsViewModel.StockEvent.Loading -> {

            }
            is CurrencyDetailsViewModel.StockEvent.SuccessAdd -> onSuccessAdd(event.isAdding)
            is CurrencyDetailsViewModel.StockEvent.SuccessUpdate -> onSuccessUpdate(event.isUpdating)
        }
    }

    private fun fillGUI(currency: Currency){
        binding.currencyHeader.setCurrencyName(currency.name, true)
        binding.currencyHeader.setStockNumber(currency.numberOfStocks)
        binding.currencyHeader.setTotalPrice(currency.totalPrice)
        binding.currencyHeader.setCalculator(true, currency.numberOfStocks, currency.totalPrice)
    }

    private fun onError(message: String?, type: Int) {
        message?.let {
            binding.componentAddStock.setError(it, type)
        }
    }

    private fun onSuccessAdd(isAdding: Boolean) {
        if(isAdding){
            binding.componentAddStock.hide()
            binding.delete.hide()
            binding.saveStockButton.hide()
            binding.componentAddStock.clearView()
            binding.addStockButton.isEnabled = true
            viewModel.getSum(args.currencyId)
        }
    }

    private fun onSuccessUpdate(isUpdate: Boolean){
        if(isUpdate){
            sharedViewModel.currencey.postValue(viewModel.tempCurrency)
        }
    }
    private fun onSuccess(data: LiveData<List<Stock>>?) {
        data?.observe(viewLifecycleOwner,{
            viewModel.data.clear()
            viewModel.data.addAll(it)
            stocksRecyclerView()
        })

    }
    private fun stocksRecyclerView(){
        if(viewModel.data.isNullOrEmpty()){
            binding.stocksRecyclerView.hide()
        }else{
            binding.stocksRecyclerView.show()
            binding.stocksRecyclerView.apply {
                adapter = StocksListAdapter(viewModel.data, deleteStock)
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }

    private fun setListener(){
        binding.addStockButton.setOnClickListener {
            if(binding.componentAddStock.visibility == View.GONE){
                binding.componentAddStock.show()
                binding.delete.show()
                binding.saveStockButton.show()
            }else{
                binding.componentAddStock.hide()
                binding.delete.hide()
                binding.saveStockButton.hide()
                binding.componentAddStock.clearView()
            }
        }

        binding.delete.setOnClickListener{
            binding.componentAddStock.hide()
            binding.delete.hide()
            binding.saveStockButton.hide()
            binding.componentAddStock.clearView()

        }

        binding.saveStockButton.setOnClickListener {
            binding.componentAddStock.getStockInformation(addNewStock)
        }

        binding.componentAddStock.calculateTotal()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.currencey = null
        viewModel.tempCurrency = null
        sharedViewModel.currencey.postValue(null)
    }
}