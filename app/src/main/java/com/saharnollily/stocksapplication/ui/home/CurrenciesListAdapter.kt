package com.saharnollily.stocksapplication.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saharnollily.stocksapplication.databinding.StockListItemBinding
import com.saharnollily.stocksapplication.models.Currency

class CurrenciesListAdapter(
    private val currencies: List<Currency>,
    private val navigateToDetails: (Currency) -> Unit
    ):RecyclerView.Adapter<CurrenciesListAdapter.CurrenciesHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesHolder {
        val binding = StockListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CurrenciesHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrenciesHolder, position: Int) {
        val currentCurrency = currencies[position]
        holder.bind(currentCurrency)
    }

    override fun getItemCount(): Int = currencies.size

    inner class CurrenciesHolder(private val binding: StockListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(currency: Currency){
            binding.currencyItem.setBackgroundColor()
            binding.currencyItem.setCurrencyName(currency.name)
            binding.currencyItem.setStockNumber(currency.numberOfStocks)
            binding.currencyItem.setTotalPrice(currency.totalPrice)

            binding.root.setOnClickListener {
                navigateToDetails(currency)
            }
        }


    }
}