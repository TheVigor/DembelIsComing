package com.noble.activity.dembeliscoming.storage

import android.content.Context
import android.content.SharedPreferences

class SoldierPrefs (context: Context) {
    private val PREFS_FILENAME = "soldier_prefs"

    private val SOLDIER_NAME = "soldier_name"

    private val SOLDIER_START_DATE = "start_date"
    private val SOLDIER_END_DATE = "end_date"

    private val SOLDIER_IMAGE_URI = "image_uri"

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

    var soldierImage: String
        get() = prefs.getString(SOLDIER_IMAGE_URI, "")
        set(value) = prefs.edit().putString(SOLDIER_IMAGE_URI, value).apply()

    fun isSoldierLoggedIn() = !soldierName.trim().isEmpty() &&
            !(startDate == 0L) && !(endDate == 0L)
}