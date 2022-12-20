package com.example.studentcrime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter(val items: List<Przewinienie>): RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>() {
    var onItemClick: ((Przewinienie) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(val item: View): RecyclerView.ViewHolder(item) {
        val tytul = item.findViewById<TextView>(R.id.itemTytul)
        val obrazek = item.findViewById<ImageView>(R.id.itemObrazek)

        fun bind(przewinienie: Przewinienie){
            tytul.text = przewinienie.tytul
            if (przewinienie.czyJestRozwiazane){
                obrazek.visibility = View.VISIBLE
            }
            else {
                obrazek.visibility = View.INVISIBLE
            }
            item.setOnClickListener {
                onItemClick?.invoke(przewinienie)
            }
        }
    }
}