package com.saharnollily.stocksapplication.utils.costum_ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.saharnollily.stocksapplication.databinding.ComponentStockListItemBinding


class ComponentStockListItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    private val binding = ComponentStockListItemBinding.inflate(LayoutInflater.from(context), this, true)

}