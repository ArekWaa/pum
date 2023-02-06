package com.example.projekt

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class Task(): RealmObject() {
    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var title: String = ""
    var description: String = ""
    var priority: String = ""
    var isCompleted: Boolean = false

    constructor(id: String?, title: String, description: String, priority: String, isCompleted: Boolean = false) : this() {
        id?.let {
            this.id = it
        }
        this.title = title
        this.description = description
        this.priority = priority
        this.isCompleted = isCompleted
    }
}
