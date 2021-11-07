package com.saharnollily.stocksapplication.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saharnollily.stocksapplication.R
import com.saharnollily.stocksapplication.databinding.ComponentStockListItemBinding
import com.saharnollily.stocksapplication.models.Stock
import com.saharnollily.stocksapplication.utils.round
import com.saharnollily.stocksapplication.utils.show

class StocksListAdapter(
    private val stocks: List<Stock>,
    private val deleteStock: (Stock, Int) -> Unit
    ):RecyclerView.Adapter<StocksListAdapter.StocksHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksHolder {
        val binding = ComponentStockListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StocksHolder(binding)
    }

    override fun onBindViewHolder(holder: StocksHolder, position: Int) {
        val currentStock = stocks[position]
        holder.bind(currentStock, position)
    }

    override fun getItemCount(): Int = stocks.size

    inner class StocksHolder(private val binding: ComponentStockListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(stock: Stock, position: Int){
            binding.purchasingPriceTextView.text = stock.purchasingPrice?.round().toString()
            binding.stockQuantityTextView.text = stock.stockQuantity?.round().toString()
            binding.totalAmountTextView.text = stock.totalAmount?.round().toString()

            binding.delete.show()

            binding.delete.setOnClickListener {
                stock.let { it1 -> deleteStock(it1,position) }
            }
        }
    }
}