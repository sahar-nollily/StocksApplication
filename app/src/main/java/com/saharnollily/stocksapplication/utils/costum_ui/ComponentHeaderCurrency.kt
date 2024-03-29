package com.saharnollily.stocksapplication.utils.costum_ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.databinding.ComponentHeaderCurrencyBinding


class ComponentHeaderCurrency @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = ComponentHeaderCurrencyBinding.inflate(LayoutInflater.from(context), this, true)

    private val currencyName = binding.currencyName
    private val stockNumber = binding.stockNumber
    private val totalPrice = binding.usdt

    fun setCurrencyName(name: String?, changeColor: Boolean= false){
        name?.let {
            currencyName.text = it.capitalize(Locale.current)
        }
        if(changeColor)
            currencyName.setTextColor(context.resources.getColor(R.color.background_1,null))

    }

    fun setStockNumber(stockNumber: Int?){
        stockNumber?.let {
            this.stockNumber.text = it.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTotalPrice(total: Float?){
        total?.let {
            totalPrice.text = "$it$"
        }
    }

    fun setBackgroundColor(){
        val d = ContextCompat.getDrawable(context, R.drawable.bg_pressed_background)
        val color = ContextCompat.getColor(context, R.color.background_5)
        val mode = PorterDuff.Mode.DST_OVER
        d?.setColorFilter(color, mode)

        binding.root.background = d
    }
}