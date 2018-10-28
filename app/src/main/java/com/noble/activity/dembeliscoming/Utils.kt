package com.noble.activity.dembeliscoming

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.noble.activity.dembeliscoming.models.DiffTime
import java.text.SimpleDateFormat
import java.util.*


val DATE_FORMAT = "dd/MM/yyyy"

val secondsInMilli: Long = 1000L
val minutesInMilli = secondsInMilli * 60L
val hoursInMilli = minutesInMilli * 60L
val daysInMilli = hoursInMilli * 24L


fun updateDate(view: View, mDay: Int, mMonth: Int, mYear: Int, isStartDate: Boolean) {

    val cal = Calendar.getInstance()
    cal.set(Calendar.YEAR, mYear)
    cal.set(Calendar.MONTH, mMonth)
    cal.set(Calendar.DAY_OF_MONTH, mDay)

    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)

    val txtDate = sdf.format(cal.time)
    val longDate = cal.timeInMillis

    (view as Button).text = txtDate

    if (isStartDate) {
        soldierPrefs.startDate = longDate
    } else {
        soldierPrefs.endDate = longDate
    }
}

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}


fun validateNewSoldier(context: Context): Boolean {
    if (soldierPrefs.soldierName.trim().isEmpty()) {
        context.showToast("Soldier name can't be empty")
        return false
    }

    if (soldierPrefs.startDate == 0L) {
        context.showToast("Start date can't be empty")
        return false
    }

    if (soldierPrefs.endDate == 0L) {
        context.showToast("End date can't be empty")
        return false
    }

    if (soldierPrefs.startDate > soldierPrefs.endDate) {
        context.showToast("End date should be after start date")
        return false
    }

    return true
}

fun getDiffTime(startDate: Long, endDate: Long) : DiffTime {

    var different = endDate - startDate

    val elapsedDays = different / daysInMilli
    different = different % daysInMilli

    val elapsedHours = different / hoursInMilli
    different = different % hoursInMilli

    val elapsedMinutes = different / minutesInMilli
    different = different % minutesInMilli

    val elapsedSeconds = different / secondsInMilli

    return DiffTime(elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds)
}