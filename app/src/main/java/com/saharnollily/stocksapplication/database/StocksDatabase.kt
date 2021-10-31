package com.saharnollily.stocksapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saharnollily.stocksapplication.data.stocks.StocksDao
import com.saharnollily.stocksapplication.models.Currency
import com.saharnollily.stocksapplication.models.Stock

@Database(entities = [Currency::class, Stock::class] , version = 1)
abstract class StocksDatabase: RoomDatabase() {
    abstract fun stocksDao(): StocksDao
}