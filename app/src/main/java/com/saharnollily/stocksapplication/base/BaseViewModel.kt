package com.saharnollily.stocksapplication.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<EVENT> : ViewModel() {

    private val _event = MutableLiveData<EVENT>()
    val event: LiveData<EVENT> = _event


    protected fun setEvent(newEvent: () -> EVENT) {
        _event.value = newEvent()
    }

    protected fun postEvent(newEvent: () -> EVENT) {
        _event.postValue(newEvent())
    }
}