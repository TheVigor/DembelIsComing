package com.noble.activity.dembeliscoming.data.note

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var day: String = ""
    ) {

    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    val isEmpty: Boolean
        get() = title.isEmpty() && description.isEmpty() && day.isEmpty()
}