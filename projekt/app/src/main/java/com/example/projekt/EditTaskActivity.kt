package com.example.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.*

class EditTaskActivity : AppCompatActivity() {
    lateinit var prioritySpinner: Spinner
    lateinit var titleTextView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var isCompletedCheckBox: CheckBox
    lateinit var editButton: Button
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        titleTextView = findViewById(R.id.editTaskTitle)
        descriptionTextView = findViewById(R.id.editTaskDescription)
        isCompletedCheckBox = findViewById(R.id.editTaskIsCompleted)
        editButton = findViewById(R.id.editTaskAddButton)
        deleteButton = findViewById(R.id.editTaskDeleteButton)
        prioritySpinner = findViewById(R.id.editTaskPriority)

        loadData()

        editButton.setOnClickListener {
            val task = Task(
                intent.getStringExtra("ID")?: "",
                titleTextView.text.toString(),
                descriptionTextView.text.toString(),
                (prioritySpinner.selectedItem as TaskPriority).toString(),
                isCompletedCheckBox.isChecked
            )

            RealmProvider.edit(task)
            finish()
        }

        deleteButton.setOnClickListener {
            RealmProvider.delete(intent.getStringExtra("ID")?: "")
            finish()
        }

//        prioritySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, TaskPriority.values())
//
//        addButton.setOnClickListener {
//            val task = Task(
//                titleTextView.text.toString(),
//                descriptionTextView.text.toString(),
//                (prioritySpinner.selectedItem as TaskPriority).toString(),
//                isCompletedCheckBox.isChecked
//            )
//
//            RealmProvider.add(task)
//            finish()
//        }
    }

    fun loadData() {
        var id = intent.getStringExtra("ID")?: ""
        var task = RealmProvider.getItem(id)
        titleTextView.text = task.title
        descriptionTextView.text = task.description
        isCompletedCheckBox.isChecked = task.isCompleted

        prioritySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, TaskPriority.values())
        prioritySpinner.setSelection(TaskPriority.valueOf(task.priority).ordinal)
    }
}