package com.saharnollily.stocksapplication.ui.add

import android.util.Log
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
class AddViewModel @Inject constructor(private val stocksRepository: StocksRepository): BaseViewModel<AddViewModel.AddEvent>() {


    fun addCurrency(name: String?) {
        viewModelScope.launch {
            setEvent { AddEvent.Loading(true) }

            if(name.isNullOrEmpty()){
                setEvent { AddEvent.Error("The name field must not be empty") }
            }else{
                val currency = Currency(0,name)
                stocksRepository.addCurrency(currency)
                setEvent { AddEvent.Success(true) }
            }
            setEvent { AddEvent.Loading(false) }
        }
    }




    sealed class AddEvent{
        data class Success( val isSuccess: Boolean): AddViewModel.AddEvent()
        data class Error(val message: String): AddViewModel.AddEvent()
        data class Loading(val isLoading: Boolean): AddViewModel.AddEvent()
    }

}
