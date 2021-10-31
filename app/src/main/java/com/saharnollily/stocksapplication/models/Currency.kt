package com.saharnollily.stocksapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true)val currencyId: Int = 0,
    val name: String = "",
    val numberOfStocks: Int = 0,
    val totalPrice: Float = 0f
)
