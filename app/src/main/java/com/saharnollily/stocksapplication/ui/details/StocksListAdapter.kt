package com.saharnollily.stocksapplication.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.databinding.ComponentStockListItemBinding
import com.saharnollily.stocksapplication.models.Stock

class StocksListAdapter(
    private val stocks: List<Stock>
    ):RecyclerView.Adapter<StocksListAdapter.StocksHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksHolder {
        val binding = ComponentStockListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StocksHolder(binding)
    }

    override fun onBindViewHolder(holder: StocksHolder, position: Int) {
        val currentStock = stocks[position]
        holder.bind(currentStock)
    }

    override fun getItemCount(): Int = stocks.size

    inner class StocksHolder(private val binding: ComponentStockListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(stock: Stock){
            setStyle(binding.purchasingPriceTextView , stock.purchasingPrice.toString())
            setStyle(binding.stockQuantityTextView , stock.stockQuantity.toString())
            setStyle(binding.totalAmountTextView , stock.totalAmount.toString())
        }

        fun setStyle(view: TextView, text: String){
            view.setTextColor(binding.root.context.resources.getColor(R.color.text_color_4,null))
            view.text = text

        }

    }
}