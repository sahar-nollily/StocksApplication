package com.saharnollily.stocksapplication.ui.home

import androidx.lifecycle.*
import com.saharnollily.stocksapplication.base.BaseViewModel
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.repository.StocksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val stocksRepository: StocksRepository): BaseViewModel<HomeViewModel.StockEvent>() {

    var currencyList = mutableListOf<Currency>()

    val getAllCurrency: LiveData<List<Currency>>  = stocksRepository.getCurrencies().asLiveData()

    fun getCurrencyById(id: Int?){
        viewModelScope.launch {
            setEvent { StockEvent.Loading(true) }

            if(id == null){
                setEvent { StockEvent.Error("invalid id") }
            }else{
                val result = stocksRepository.getCurrencyById(id)
                setEvent { StockEvent.Success(result) }
            }
            setEvent { StockEvent.Loading(false) }
        }
    }

    fun deleteCurrency(id: Int){
        viewModelScope.launch {
            stocksRepository.deleteCurrency(id)
        }
    }

    fun updateCurrencyName(id: Int, name: String){
        viewModelScope.launch {
            stocksRepository.updateCurrencyName(id, name)
        }
    }

    sealed class StockEvent{
        data class Success( val data: Currency?): HomeViewModel.StockEvent()
        data class Error(val message: String): HomeViewModel.StockEvent()
        data class Loading(val isLoading: Boolean): HomeViewModel.StockEvent()
    }

}
