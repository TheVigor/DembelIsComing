package com.noble.activity.dembeliscoming.data.drill

import android.accounts.AuthenticatorDescription
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "drill_docs")
data class DrillDoc(
    @PrimaryKey @ColumnInfo(name = "id") val docId: String,
    val type: String = "drill",
    val name: String,
    val description: String
) {
    override fun toString() = name
}