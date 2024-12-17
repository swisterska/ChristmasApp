package com.example.christmasapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val history: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        // Inflate the default simple list item layout
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return HistoryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val message = history[position]
        holder.textView.text = message

        // Set text color directly
        holder.textView.setTextColor(
            when {
                message.contains("💌") -> Color.GREEN
                message.contains("🎁") -> Color.YELLOW
                message.contains("Grinch") -> Color.RED
                else -> Color.BLACK
            }
        )
    }

    override fun getItemCount(): Int = history.size
}