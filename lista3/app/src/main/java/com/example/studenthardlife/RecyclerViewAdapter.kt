package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val data: List<Task>, val onItemClicked: (Int) -> Unit): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView: TextView
        init {
            textView = view.findViewById(R.id.itemText)
        }

        fun bind(task: Task){
            textView.text = task.title
        }

    }


}