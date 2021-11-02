package com.saharnollily.stocksapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saharnollily.stocksapplication.models.Currency

class SharedViewModel: ViewModel() {
    val currencey = MutableLiveData<Currency>()
}