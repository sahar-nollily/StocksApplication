package com.saharnollily.stocksapplication.data.stocks

import androidx.lifecycle.LiveData
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import kotlinx.coroutines.flow.Flow

interface StocksDataSource {

    suspend fun addCurrency(currency: Currency)

    suspend fun addStock(stock: Stock)

    suspend fun updateCurrency(currency: Currency)

    fun getCurrencies(): Flow<List<Currency>>

    suspend fun getCurrencyById(id: Int): Currency

    fun getCurrencyInformation(id: Int): Flow<List<Stock>>

    suspend fun deleteCurrency(id: Int)

    suspend fun updateCurrencyName(id: Int, name: String)

    suspend fun getPurchasingPriceSum(id: Int): Float?


}