package com.saharnollily.stocksapplication.data.stocks

import androidx.lifecycle.LiveData
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksDataSourceImp @Inject constructor(private val stocksDao: StocksDao): StocksDataSource {

    override suspend fun addCurrency(currency: Currency) {
        stocksDao.addCurrency(currency)
    }

    override suspend fun addStock(stock: Stock) {
        stocksDao.addStock(stock)
    }

    override suspend fun updateCurrency(currency: Currency) {
        stocksDao.updateCurrency(currency)
    }

    override fun getCurrencies(): Flow<List<Currency>> {
        return stocksDao.getCurrencies()
    }

    override suspend fun getCurrencyById(id: Int): Currency {

        return stocksDao.getCurrencyById(id)
    }

    override fun getCurrencyInformation(id: Int): Flow<List<Stock>> {
        return stocksDao.getCurrencyInformation(id)
    }

    override suspend fun deleteCurrency(id: Int) {
        return stocksDao.deleteCurrency(id)
    }

    override suspend fun updateCurrencyName(id: Int, name: String) {
        return stocksDao.updateCurrencyName(id, name)
    }

    override suspend fun getPurchasingPriceSum(id: Int): Float? {
        return stocksDao.getPurchasingPriceSum(id)
    }
}