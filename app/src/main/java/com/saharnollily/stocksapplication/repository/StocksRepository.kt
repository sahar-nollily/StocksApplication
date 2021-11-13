package com.saharnollily.stocksapplication.repository

import androidx.lifecycle.LiveData
import com.saharnollily.stocksapplication.data.stocks.StocksDataSource
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksRepository @Inject constructor(private val stocksDataSource: StocksDataSource) {

    suspend fun addCurrency(currency: Currency) {
        stocksDataSource.addCurrency(currency)
    }

    suspend fun addStock(stockInformation: Stock) {
        stocksDataSource.addStock(stockInformation)
    }

    suspend fun updateCurrency(currency: Currency){
        stocksDataSource.updateCurrency(currency)
    }

    fun getCurrencies(): Flow<List<Currency>> {
        return stocksDataSource.getCurrencies()
    }

    suspend fun getCurrencyById(id: Int): Currency {
        return stocksDataSource.getCurrencyById(id)
    }

    fun getCurrencyInformation(id: Int): Flow<List<Stock>> {
        return stocksDataSource.getCurrencyInformation(id)
    }

    suspend fun deleteCurrency(id: Int){
        return stocksDataSource.deleteCurrency(id)
    }

    suspend fun deleteStock(id: Int){
        return stocksDataSource.deleteStock(id)
    }

    suspend fun updateCurrencyName(id: Int, name: String){
        return stocksDataSource.updateCurrencyName(id, name)
    }

    suspend fun getPurchasingPriceSum(id: Int): Float?{
        return stocksDataSource.getPurchasingPriceSum(id)
    }

    suspend fun getTotalPriceSum(): Float? {
        return stocksDataSource.getTotalPriceSum()
    }

}