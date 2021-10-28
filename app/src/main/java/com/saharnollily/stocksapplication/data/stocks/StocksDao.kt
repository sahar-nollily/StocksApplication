package com.saharnollily.stocksapplication.data.stocks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.CurrencyInformation
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {

    @Insert
    suspend fun addCurrency(currency: Currency)

    @Insert
    suspend fun addStock(currencyInformation: CurrencyInformation)

    @Query("SELECT * FROM Currency")
    fun getCurrencies(): Flow<List<Currency>>

    @Query("SELECT * From Currency WHERE currencyId = :id")
    suspend fun getCurrencyById(id: Int): Currency

    @Query("SELECT * From CurrencyInformation WHERE currencyId =:id ")
    fun getCurrencyInformation(id: Int): Flow<List<CurrencyInformation>>
}