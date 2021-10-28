package com.saharnollily.stocksapplication.data.stocks

import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.CurrencyInformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksDataSourceImp @Inject constructor(private val stocksDao: StocksDao): StocksDataSource {

    override suspend fun addCurrency(currency: Currency) {
        stocksDao.addCurrency(currency)
    }

    override suspend fun addStock(currencyInformation: CurrencyInformation) {
        stocksDao.addStock(currencyInformation)
    }

    override fun getCurrencies(): Flow<List<Currency>> {
        return stocksDao.getCurrencies()
    }

    override suspend fun getCurrencyById(id: Int): Currency {

        return stocksDao.getCurrencyById(id)
    }

    override fun getCurrencyInformation(id: Int): Flow<List<CurrencyInformation>> {
        return stocksDao.getCurrencyInformation(id)
    }
}