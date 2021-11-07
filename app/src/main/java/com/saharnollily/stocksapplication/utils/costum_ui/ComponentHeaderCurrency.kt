package com.saharnollily.stocksapplication.utils.costum_ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.databinding.ComponentHeaderCurrencyBinding
import com.saharnollily.stocksapplication.utils.hide
import com.saharnollily.stocksapplication.utils.invisible
import com.saharnollily.stocksapplication.utils.show


class ComponentHeaderCurrency @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = ComponentHeaderCurrencyBinding.inflate(LayoutInflater.from(context), this, true)

    private val currencyName = binding.currencyName
    private val stockNumber = binding.stockNumber
    private val totalPrice = binding.usdt
    private val calculatorLinearLayout = binding.linearLayout
    private val stockPrice = binding.stockPrice
    private val newUSDT = binding.newUSDT
    private val editIcon = binding.edit
    private val currencyNameEditText = binding.currencyNameEditText
    private val priceDifference = binding.priceDifference


    fun setCurrencyName(name: String?, changeColor: Boolean= false){
        name?.let {
            currencyName.text = it.capitalize(Locale.current)
        }
        if(changeColor)
            currencyName.setTextColor(context.resources.getColor(R.color.text_color_6,null))

    }

    fun setStockNumber(stockNumber: Float?){
        stockNumber?.let {
            this.stockNumber.text = it.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTotalPrice(total: Float?){
        total?.let {
            totalPrice.text = "${it}$"
        }
    }

    fun setBackgroundColor(){
        val d = ContextCompat.getDrawable(context, R.drawable.bg_pressed_background)
        val color = ContextCompat.getColor(context, R.color.background_5)
        val mode = PorterDuff.Mode.DST_OVER
        d?.setColorFilter(color, mode)

        binding.root.background = d
    }

    fun setCalculator(showCalculator: Boolean = false, stockNumber: Float = 0f, totalPrice: Float ){
        if(showCalculator){
            calculatorLinearLayout.show()
        }else{
            calculatorLinearLayout.hide()
        }

        newUSDT.setText("${totalPrice}")



        stockPrice.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty() && totalPrice != 0f) {
                    newUSDT.setText(
                        "${stockNumber * p0.toString().toFloat()}"
                    )
                    val round = newUSDT.text.toString().toFloat() - totalPrice
                    priceDifference.show()
                    priceDifference.text = "${context.getString(R.string.price_difference)} ${round}"
                        stockPrice.error = null
                }else if(p0.isNullOrEmpty()){
                    stockPrice.error = null
                }
                else if(totalPrice == 0f){
                    stockPrice.error = context.getString(R.string.no_stock)
                }else
                    stockPrice.error = null
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun showEditIcon(showing: Boolean = false, updateItem: (String, Int) -> Unit, currencyId: Int){
        if (showing)
            editIcon.show()
        else
            editIcon.hide()

        editIcon.setOnClickListener {
            if(currencyNameEditText.visibility == View.VISIBLE){
                updateItem(currencyNameEditText.text.toString(), currencyId)
                currencyName.show()
                currencyNameEditText.invisible()
            }
            else{
                currencyNameEditText.setText(currencyName.text)
                currencyNameEditText.show()
                currencyName.hide()
            }
        }
    }


}