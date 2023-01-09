package com.example.studenthardlife

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.icu.text.CaseMap.Title

object DataProvider {
    var sharedPreferences: SharedPreferences? = null
    fun init(activity: Activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        }

        if ((sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf<String>()).isEmpty()) {
            sharedPreferences?.edit()
                ?.putString("0_0", "Zadanie 1")?.putString("0_1", "Opis zadania 1.")
                ?.putString("1_0", "Zadanie 2")?.putString("1_1", "Opis zadania 2.")
                ?.apply()
            sharedPreferences?.edit()
                ?.putStringSet("ID", mutableSetOf<String>("0", "1"))
                ?.apply()
        }
    }

    fun getAllItems(): List<Task> {
        val result = mutableListOf<Task>()
        val ids = sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf()
        for (i in ids) {
            val title = sharedPreferences?.getString("${i}_0", "") ?: ""
            val description = sharedPreferences?.getString("${i}_1", "") ?: ""
            result.add(Task(title, description))
        }
        return result
    }

    fun getItem(position: Int): Task {
        val ids = sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf()
        val id = ids.toList()[position]
        val title = sharedPreferences?.getString("${id}_0", "") ?: ""
        val description = sharedPreferences?.getString("${id}_1", "") ?: ""
        return Task(title, description)
    }

    fun setSelectedItem(position: Int) {
        sharedPreferences?.edit()
            ?.putInt("SELECTED", position)
            ?.apply()
    }

    fun getSelectedItem(): Task {
        val position = sharedPreferences?.getInt("SELECTED", 0) ?:0
        return getItem(position)
    }

    fun editItem(task: Task) {
        val position = sharedPreferences?.getInt("SELECTED", 0)?: 0
        val ids = sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf()
        val id = ids.toList()[position]
        sharedPreferences?.edit()
            ?.putString("${id}_0", task.title)
            ?.putString("${id}_1", task.description)
            ?.apply()
    }

    fun addItem(task: Task) {
        var id = "0"
        var ids = mutableSetOf<String>()
        if (task.id == -1) {
            ids = sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf()
            if (ids.size > 0) {
                id = (ids.last().toInt() + 1).toString()
            }
        }
        else {
            id = task.id.toString()
        }

        sharedPreferences?.edit()
            ?.putString("${id}_0", task.title)
            ?.putString("${id}_1", task.description)
            ?.apply()
        ids.add(id)
        sharedPreferences?.edit()
            ?.putStringSet("ID", ids)
            ?.apply()
    }

    fun deleteCurrentItem() {
        val ids = sharedPreferences?.getStringSet("ID", mutableSetOf())?: mutableSetOf()
        val position = sharedPreferences?.getInt("SELECTED", 0)?: 0
        val id = ids.toList()[position]

        sharedPreferences?.edit()
            ?.remove("${id}_0")
            ?.remove("${id}_1")
            ?.apply()
        ids.remove(id)
        sharedPreferences?.edit()
            ?.putStringSet("ID", ids)
            ?.apply()
    }

}