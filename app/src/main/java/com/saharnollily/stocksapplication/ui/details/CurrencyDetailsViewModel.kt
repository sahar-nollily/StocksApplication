package com.saharnollily.stocksapplication.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saharnollily.stocksapplication.base.BaseViewModel
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import com.saharnollily.stocksapplication.repository.StocksRepository
import com.saharnollily.stocksapplication.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailsViewModel @Inject constructor(private val stocksRepository: StocksRepository): BaseViewModel<CurrencyDetailsViewModel.StockEvent>() {

    var currencey : Currency? = null
    var tempCurrency: Currency? = null
    var data = mutableListOf<Stock>()
    val getPurchasingPriceSum = MutableLiveData<Float>()


    fun addCurrencyInformation(currencyId: Int, stockQuantity: Float, purchasingPrice: Float, totalAmount: Float) {
        viewModelScope.launch {
            setEvent { StockEvent.Loading(true) }
            when {
                stockQuantity == 0f -> {
                    setEvent { StockEvent.Error("The quantity must be greater than 0", 0) }
                }
                purchasingPrice == 0f -> {
                    setEvent { StockEvent.Error("The purchasing price must be greater than 0", 1) }
                }
                totalAmount == 0f -> {
                    setEvent { StockEvent.Error("There is something wrong", 2) }
                }
                else -> {
                    val currencyInformation = Stock(null,currencyId,stockQuantity, purchasingPrice, totalAmount)
                    stocksRepository.addStock(currencyInformation)
                    setEvent { StockEvent.SuccessAdd(true) }
                    updateCurrency(stockQuantity, totalAmount)
                }
            }
            setEvent { StockEvent.Loading(false) }
        }
    }


    fun getCurrencyInformation(id: Int){
        viewModelScope.launch {
            val result = stocksRepository.getCurrencyInformation(id)
            setEvent { StockEvent.Success(result.asLiveData()) }
        }
    }

    private fun updateCurrency(stockQuantity: Float, totalAmount: Float){
        currencey?.let {
            tempCurrency = Currency(
                it.currencyId,
                it.name,
                it.numberOfStocks + stockQuantity,
                it.totalPrice + totalAmount
            )
        }

        viewModelScope.launch {
            tempCurrency?.let { stocksRepository.updateCurrency(it) }
            setEvent { StockEvent.SuccessUpdate(true) }
        }
    }

    fun getSum(id: Int){
        viewModelScope.launch {
            val result = stocksRepository.getPurchasingPriceSum(id)
            if(result != null)
                getPurchasingPriceSum.postValue(result)

        }
    }

    fun deleteStock(id: Int){
        viewModelScope.launch {
            stocksRepository.deleteStock(id)
        }
    }




    sealed class StockEvent{
        data class SuccessUpdate(val isUpdating: Boolean): CurrencyDetailsViewModel.StockEvent()
        data class SuccessAdd(val isAdding: Boolean): CurrencyDetailsViewModel.StockEvent()
        data class Success( val data: LiveData<List<Stock>>?): CurrencyDetailsViewModel.StockEvent()
        data class Error(val message: String, val type: Int): CurrencyDetailsViewModel.StockEvent()
        data class Loading(val isLoading: Boolean): CurrencyDetailsViewModel.StockEvent()
    }

}
