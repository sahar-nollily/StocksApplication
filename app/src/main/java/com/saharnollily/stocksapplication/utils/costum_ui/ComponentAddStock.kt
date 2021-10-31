package com.saharnollily.stocksapplication.utils.costum_ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.saharnollily.stocksapplication.databinding.ComponentAddStockBinding
import com.saharnollily.stocksapplication.utils.round


class ComponentAddStock @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = ComponentAddStockBinding.inflate(LayoutInflater.from(context), this, true)

    private val stockQuantity = binding.stockQuantity
    private val purchasingPrice = binding.purchasingPrice
    private val totalAmount = binding.totalAmount


    fun clearView(){
        stockQuantity.setText("")
        purchasingPrice.setText("")
        totalAmount.setText("")
        stockQuantity.error = null
        purchasingPrice.error = null
        totalAmount.error = null
    }

    fun getStockQuantity(): Int{
        return if(stockQuantity.text.isNullOrEmpty())
            0
        else
            stockQuantity.text.toString().toInt()
    }

    fun getPurchasingPrice(): Float{
        return if(purchasingPrice.text.isNullOrEmpty())
            0f
        else
            purchasingPrice.text.toString().toFloat()
    }

    fun getTotalAmount(): Float{
        return if(totalAmount.text.isNullOrEmpty())
            0f
        else
            totalAmount.text.toString().toFloat()
    }

    fun getStockInformation(addNewStock : (Int,Float,Float) -> Unit){
        addNewStock(getStockQuantity(),getPurchasingPrice(),getTotalAmount())
    }

    fun calculateTotal(){

        purchasingPrice.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty() && getStockQuantity() != 0 && getPurchasingPrice() != 0f)
                totalAmount.setText(
                    "${getStockQuantity()*p0.toString().toFloat()}"
                )

                if(p0.isNullOrEmpty()){
                    totalAmount.setText(
                        "${getStockQuantity()}"
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        stockQuantity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.isNullOrEmpty() && getStockQuantity() != 0 && getPurchasingPrice() != 0f )
                    totalAmount.setText(
                        "${getPurchasingPrice()*p0.toString().toFloat()}"
                    )

                if(p0.isNullOrEmpty()){
                    totalAmount.setText(
                        "${getPurchasingPrice()}"
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun setError(message: String, type: Int){
        when(type){
            0 -> {
                stockQuantity.error = message
            }
            1 -> {
                purchasingPrice.error = message
            }
        }
    }
}
