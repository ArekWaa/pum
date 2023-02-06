package com.example.projekt

import android.content.Context
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration


object RealmProvider {
    lateinit var realm: Realm

    fun init(context: Context) {
        Realm.init(context)
        val config = RealmConfiguration.Builder().allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
    }

    fun add(task: Task) {
        try {
            realm.executeTransaction{
                realm.insert(task)
            }
        }
        catch (e: Exception) {
            Log.d("realm", "error adding data to realm")
        }
    }

    fun edit(task: Task) {
        try {
            var query = realm.where(Task::class.java)
            var taskObject = query.findAll().filter {
                it.id == task.id
            }.first()
            realm.executeTransaction{
                taskObject.title = task.title
                taskObject.description = task.description
                taskObject.priority = task.priority
                taskObject.isCompleted = task.isCompleted
            }
        }
        catch (e: Exception) {
            Log.d("realm", "error adding data to realm")
        }
    }

    fun delete(id: String) {
        try {

            realm.executeTransaction{
                var query = realm.where(Task::class.java)
                var taskObject = query.findAll().filter {
                    it.id == id
                }.first()
               taskObject.deleteFromRealm()
            }
        }
        catch (e: Exception) {
            Log.d("realm", "error adding data to realm")
        }
    }

    fun deleteAllCompleted() {
        try {

            realm.executeTransaction{
                var query = realm.where(Task::class.java)
                var taskList = query.findAll().filter {
                    it.isCompleted
                }
                taskList.forEach {
                    it.deleteFromRealm()
                }
            }
        }
        catch (e: Exception) {
            Log.d("realm", "error adding data to realm")
        }
    }

    fun getAllSorted(): List<Task> {
        try {
            var query = realm.where(Task::class.java)
            return query.findAll().sortedBy {
                TaskPriority.valueOf(it.priority)
            }
        }
        catch (e: Exception) {
            Log.d("realm", "error getting data from realm")
        }
        return emptyList()
    }

    fun getItem(id: String): Task {
        try {
            var query = realm.where(Task::class.java)
            return query.findAll().filter {
                it.id == id
            }.first()
        }
        catch (e: Exception) {
            Log.d("realm", "error getting data from realm")
        }
        return Task()
    }
}