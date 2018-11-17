package com.noble.activity.dembeliscoming.data.internal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "internal_docs")
data class InternalDoc(
    @PrimaryKey @ColumnInfo(name = "id") val docId: String,
    val type: String = "internal",
    val name: String,
    val description: String
) {
    override fun toString() = name
}
