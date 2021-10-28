package com.saharnollily.stocksapplication.hilt

import android.content.Context
import androidx.room.Room
import com.saharnollily.stocksapplication.data.stocks.StocksDao
import com.saharnollily.stocksapplication.database.StocksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): StocksDatabase =
        Room.databaseBuilder(context,StocksDatabase::class.java,"app_db").build()


    @Provides
    fun provideStocksDao(stocksDatabase: StocksDatabase): StocksDao = stocksDatabase.stocksDao()

}