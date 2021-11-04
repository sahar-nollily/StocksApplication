package com.saharnollily.stocksapplication.data.stocks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {

    @Insert
    suspend fun addCurrency(currency: Currency)

    @Insert
    suspend fun addStock(stock: Stock)

    @Update
    suspend fun updateCurrency(currency: Currency)

    @Query("SELECT * FROM Currency")
    fun getCurrencies(): Flow<List<Currency>>

    @Query("SELECT * From Currency WHERE currencyId = :id")
    suspend fun getCurrencyById(id: Int): Currency

    @Query("SELECT * From Stock WHERE currencyId =:id ")
    fun getCurrencyInformation(id: Int): Flow<List<Stock>>

    @Query("DELETE FROM Currency WHERE currencyId = :id")
    suspend fun deleteCurrency(id: Int)

    @Query("DELETE FROM Stock WHERE stockId = :id")
    suspend fun deleteStock(id: Int)

    @Query("UPDATE Currency SET name = :name WHERE currencyId = :id")
    suspend fun updateCurrencyName(id: Int, name: String)

    @Query("SELECT SUM(purchasingPrice) FROM stock WHERE currencyId = :id")
    suspend fun getPurchasingPriceSum(id: Int): Float?
}