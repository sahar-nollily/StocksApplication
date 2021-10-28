package com.saharnollily.stocksapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StocksApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}