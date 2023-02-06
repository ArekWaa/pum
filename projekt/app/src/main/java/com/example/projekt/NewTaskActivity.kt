package com.example.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.*

class NewTaskActivity : AppCompatActivity() {
    lateinit var prioritySpinner: Spinner
    lateinit var titleTextView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var isCompletedCheckBox: CheckBox
    lateinit var addButton: Button
    lateinit var shoppingButton: Button
    lateinit var garbageButton: Button
    lateinit var dishesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        titleTextView = findViewById(R.id.newTaskTitle)
        descriptionTextView = findViewById(R.id.newTaskDescription)
        isCompletedCheckBox = findViewById(R.id.newTaskIsCompleted)
        addButton = findViewById(R.id.newTaskAddButton)
        prioritySpinner = findViewById(R.id.newTaskPriority)
        shoppingButton = findViewById(R.id.ShoppingButton)
        garbageButton = findViewById(R.id.garbageButton)
        dishesButton = findViewById(R.id.dishesButton)

        prioritySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, TaskPriority.values())

        addButton.setOnClickListener {
            val task = Task(
                null,
                titleTextView.text.toString(),
                descriptionTextView.text.toString(),
                (prioritySpinner.selectedItem as TaskPriority).toString(),
                isCompletedCheckBox.isChecked
            )

            RealmProvider.add(task)
            finish()
        }

        shoppingButton.setOnClickListener {
            titleTextView.text = "Shopping"
            descriptionTextView.text = "Every day groceries"
            isCompletedCheckBox.isChecked = false
            prioritySpinner.setSelection(TaskPriority.valueOf("LOW").ordinal)
        }

        garbageButton.setOnClickListener {
            titleTextView.text = "Garbage"
            descriptionTextView.text = "Remember to sort garbage"
            isCompletedCheckBox.isChecked = false
            prioritySpinner.setSelection(TaskPriority.valueOf("LOW").ordinal)
        }

        dishesButton.setOnClickListener {
            titleTextView.text = "Dishes"
            descriptionTextView.text = "Dishes put into the dishwasher"
            isCompletedCheckBox.isChecked = false
            prioritySpinner.setSelection(TaskPriority.valueOf("LOW").ordinal)
        }
    }
}