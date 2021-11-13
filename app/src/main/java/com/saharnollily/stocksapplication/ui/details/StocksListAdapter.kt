package com.saharnollily.stocksapplication.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saharnollily.stocksapplication.databinding.ComponentStockListItemBinding
import com.saharnollily.stocksapplication.models.Stock
import com.saharnollily.stocksapplication.utils.actualNumber
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
            binding.purchasingPriceTextView.text = stock.purchasingPrice.actualNumber()
            binding.stockQuantityTextView.text = stock.stockQuantity.actualNumber()
            binding.totalAmountTextView.text = stock.totalAmount.actualNumber()

            binding.delete.show()

            binding.delete.setOnClickListener {
                stock.let { it1 -> deleteStock(it1,position) }
            }
        }
    }
}