package com.example.restcountiresapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CapitalsRecyclerViewAdapter(private val items: List<RecyclerViewItems>): RecyclerView.Adapter<CapitalsRecyclerViewAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.capitals_recycler_view_item, parent, false))
    }

    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: RecyclerViewItems) {
            val country = view.findViewById<TextView>(R.id.capitalsCountryName)
            val capital = view.findViewById<TextView>(R.id.capitalCapitalName)
            country.text = item.country.common
            capital.text = item.capital?.get(0)?: ""
        }
    }
}