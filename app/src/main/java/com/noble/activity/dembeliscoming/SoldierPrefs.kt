package com.noble.activity.dembeliscoming

import android.content.Context
import android.content.SharedPreferences

class SoldierPrefs (context: Context) {
    val PREFS_FILENAME = "soldier_prefs"

    val SOLDIER_NAME = "soldier_name"

    val SOLDIER_START_DATE = "start_date"
    val SOLDIER_END_DATE = "end_date"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var soldierName: String
        get() = prefs.getString(SOLDIER_NAME, "")
        set(value) = prefs.edit().putString(SOLDIER_NAME, value).apply()

    var startDate: Long
        get() = prefs.getLong(SOLDIER_START_DATE, 0)
        set(value) = prefs.edit().putLong(SOLDIER_START_DATE, value).apply()

    var endDate: Long
        get() = prefs.getLong(SOLDIER_END_DATE, 0)
        set(value) = prefs.edit().putLong(SOLDIER_END_DATE, value).apply()

    fun isSoldierLoggedIn() = !soldierName.trim().isEmpty() &&
            !(startDate == 0L) && !(endDate == 0L)
}