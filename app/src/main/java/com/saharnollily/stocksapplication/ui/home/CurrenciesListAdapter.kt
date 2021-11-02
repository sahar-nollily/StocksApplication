package com.saharnollily.stocksapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saharnollily.stocksapplication.databinding.StockListItemBinding
import com.saharnollily.stocksapplication.models.Currency

class CurrenciesListAdapter(
    private val currencies: MutableList<Currency>,
    private val navigateToDetails: (Currency) -> Unit,
    private val deleteItem: (Int, Int) -> Unit,
    private val updateItem: (String, Int) -> Unit
    ):RecyclerView.Adapter<CurrenciesListAdapter.CurrenciesHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesHolder {
        val binding = StockListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CurrenciesHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrenciesHolder, position: Int) {
        val currentCurrency = currencies[position]
        holder.bind(currentCurrency, position)
    }

    override fun getItemCount(): Int = currencies.size

    inner class CurrenciesHolder(private val binding: StockListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(currency: Currency, position: Int){
            binding.currencyItem.setBackgroundColor()
            binding.currencyItem.setCurrencyName(currency.name)
            binding.currencyItem.setStockNumber(currency.numberOfStocks)
            binding.currencyItem.setTotalPrice(currency.totalPrice)
            binding.currencyItem.showEditIcon(true, updateItem, currency.currencyId)

            binding.root.setOnClickListener {
                navigateToDetails(currency)
            }


            binding.root.setOnLongClickListener {
                deleteItem(position, currency.currencyId)
                return@setOnLongClickListener true
            }
        }


    }
}