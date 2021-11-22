package com.example.retrofit_practice_mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice_mvvm.R
import com.example.retrofit_practice_mvvm.models.Result

class QuoteAdapter: ListAdapter<Result, QuoteAdapter.QuoteViewHolder>(QuoteDiffUtil()) {

    inner class QuoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val contentTextView = itemView.findViewById<TextView>(R.id.contentTexview)
        val authorTextView = itemView.findViewById<TextView>(R.id.authorTextview)

        fun setData(result: Result) {
            contentTextView.text = result.content
            authorTextView.text = result.author
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.setData(item)
    }

}
class QuoteDiffUtil : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result) =
        oldItem.quoteID == newItem.quoteID

    override fun areContentsTheSame(oldItem: Result, newItem: Result) =
        oldItem == newItem
}