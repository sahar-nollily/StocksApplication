package com.saharnollily.stocksapplication.repository

import com.saharnollily.stocksapplication.data.stocks.StocksDataSource
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.CurrencyInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksRepository @Inject constructor(private val stocksDataSource: StocksDataSource) {

    suspend fun addCurrency(currency: Currency) {
        stocksDataSource.addCurrency(currency)
    }

    suspend fun addStock(stockInformation: CurrencyInformation) {
        stocksDataSource.addStock(stockInformation)
    }

    fun getCurrencies(): Flow<List<Currency>> {
        return stocksDataSource.getCurrencies()
    }

    suspend fun getCurrencyById(id: Int): Currency {
        return stocksDataSource.getCurrencyById(id)
    }

    fun getCurrencyInformation(id: Int): Flow<List<CurrencyInformation>> {
        return stocksDataSource.getCurrencyInformation(id)
    }
}