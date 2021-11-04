package com.saharnollily.stocksapplication.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Currency::class,
    parentColumns = arrayOf("currencyId"),
    childColumns = arrayOf("currencyId"),
    onDelete = ForeignKey.CASCADE
)])
data class Stock(
    @PrimaryKey(autoGenerate = true)val stockId: Int? = 0,
    val currencyId: Int? = 0,
    val stockQuantity: Float? = 0f,
    val purchasingPrice: Float? = 0f,
    val totalAmount: Float? = 0f
)
