package com.saharnollily.stocksapplication.data.stocks

import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.CurrencyInformation
import kotlinx.coroutines.flow.Flow

interface StocksDataSource {

    suspend fun addCurrency(currency: Currency)

    suspend fun addStock(currencyInformation: CurrencyInformation)

    fun getCurrencies(): Flow<List<Currency>>

    suspend fun getCurrencyById(id: Int): Currency

    fun getCurrencyInformation(id: Int): Flow<List<CurrencyInformation>>

}