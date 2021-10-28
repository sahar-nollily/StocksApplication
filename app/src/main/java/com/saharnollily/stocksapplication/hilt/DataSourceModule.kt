package com.saharnollily.stocksapplication.hilt

import com.saharnollily.stocksapplication.data.stocks.StocksDataSource
import com.saharnollily.stocksapplication.data.stocks.StocksDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindStocksDataSource(stocksDataSourceImp: StocksDataSourceImp): StocksDataSource
}