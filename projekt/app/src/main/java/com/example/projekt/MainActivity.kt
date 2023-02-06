package com.example.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var newTaskButton: Button
    lateinit var deleteCompletedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RealmProvider.init(this)

        recyclerView = findViewById(R.id.taskListRecyclerView)
        newTaskButton = findViewById(R.id.addNewTaskButton)
        deleteCompletedButton = findViewById(R.id.deleteCompletedButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = TaskListAdapter(RealmProvider.getAllSorted())
        adapter.setOnItemSelectedListener(this::onItemSelected)
        recyclerView.adapter = adapter


        newTaskButton.setOnClickListener {
            val intent = Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }

        deleteCompletedButton.setOnClickListener {
            RealmProvider.deleteAllCompleted()
            loadData()
        }
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }

    fun loadData() {
        (recyclerView.adapter as TaskListAdapter).setItems(RealmProvider.getAllSorted())

    }

    fun onItemSelected(id: String) {
        val intent = Intent(this, EditTaskActivity:: class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}