package com.example.projekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(var taskList: List<Task>): RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    private var onItemSelected: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(taskList[position])
        holder.setCLickCallback(onItemSelected)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setItems(list: List<Task>) {
        taskList = list
        notifyDataSetChanged()
    }

    fun setOnItemSelectedListener(callback: ((String) -> Unit)) {
        this.onItemSelected = callback
    }

    class TaskListViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        var callback: ((String) -> Unit) ?= null

        fun bind(item: Task) {
            itemView.findViewById<TextView>(R.id.itemTitle).text = item.title
            itemView.findViewById<TextView>(R.id.itemPriority).text = item.priority.toString()
            itemView.findViewById<ImageView>(R.id.itemIsCompleted).visibility = if (item.isCompleted) {
                View.VISIBLE
            }
            else {
                View.INVISIBLE
            }

            itemView.setOnClickListener{
                this.callback?.invoke(item.id)
            }
        }

        fun setCLickCallback(callback: ((String) -> Unit)?) {
            this.callback = callback
        }
    }


}