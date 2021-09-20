package com.vincent.forexledger.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vincent.forexledger.R
import com.vincent.forexledger.model.book.BookListVO
import com.vincent.forexledger.utils.FormatUtils
import java.lang.StringBuilder
import kotlin.math.abs

class BookListAdapter(var books: List<BookListVO>)
    : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_summary, parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val itemView = holder.itemView
        val book = books[position]
        val twdProfitValue = book.twdProfit

        itemView.findViewById<ImageView>(R.id.imgCurrency).setImageResource(book.currencyType.imageResource)
        itemView.findViewById<TextView>(R.id.textName).text = book.name
        itemView.findViewById<TextView>(R.id.textBalance).text = FormatUtils.formatMoney(book.balance)
        itemView.findViewById<TextView>(R.id.textCurrency).text = book.currencyType.name

        val profitSign = if (twdProfitValue > 0) "+" else ""
        val twdProfitStr = FormatUtils.formatMoney(twdProfitValue)
        val profitPercentageStr = FormatUtils.formatMoney(abs(book.profitRate) * 100)

        val textViewProfit = itemView.findViewById<TextView>(R.id.textProfit)
//        textViewProfit.text = "$profitSign$twdProfitStr ($profitPercentageStr%)"
        textViewProfit.text = "$profitSign$twdProfitStr"

        if (twdProfitValue > 0) {
            textViewProfit.setTextColor(holder.getContext().getColor(R.color.profit_positive))
        } else if (twdProfitValue < 0) {
            textViewProfit.setTextColor(holder.getContext().getColor(R.color.profit_negative))
        }
    }

    fun refreshData(data: List<BookListVO>) {
        books = data
        notifyDataSetChanged()
    }

    inner class BookListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun getContext(): Context = view.context
    }

}