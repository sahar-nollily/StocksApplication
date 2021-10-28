package com.saharnollily.stocksapplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saharnollily.stocksapplication.base.BaseViewModel
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.repository.StocksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailsViewModel @Inject constructor(private val stocksRepository: StocksRepository): BaseViewModel<CurrencyDetailsViewModel.StockEvent>() {

    val getAllCurrency: LiveData<List<Currency>> = stocksRepository.getCurrencies().asLiveData()


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


    sealed class StockEvent{
        data class Success( val data: Currency?): CurrencyDetailsViewModel.StockEvent()
        data class Error(val message: String): CurrencyDetailsViewModel.StockEvent()
        data class Loading(val isLoading: Boolean): CurrencyDetailsViewModel.StockEvent()
    }

}
