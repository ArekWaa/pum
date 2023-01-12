package com.example.restcountiresapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FlagsRecyclerViewAdapter(private val items: List<RecyclerViewItems>): RecyclerView.Adapter<FlagsRecyclerViewAdapter.ItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flags_recycler_view_item, parent, false))
    }

    class ItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: RecyclerViewItems) {
            val country = view.findViewById<TextView>(R.id.flagsCountryName)
            val flag = view.findViewById<ImageView>(R.id.flagsCountryFlag)
            country.text = item.country.common
            Glide.with(view).load(item.flag.flag).fitCenter().into(flag)
        }
    }
}