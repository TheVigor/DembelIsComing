package com.noble.activity.dembeliscoming

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.noble.activity.dembeliscoming.models.DiffTime
import com.noble.activity.dembeliscoming.ui.DembelTimerView
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.timer_main.view.*
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

fun percentagePassed(currentDate: Long): String {
    val passed = (currentDate - soldierPrefs.startDate).toDouble()
    val all = (soldierPrefs.endDate - soldierPrefs.startDate).toDouble()

    return "%.6f".format((passed / all) * 100)

}

fun percentageLeft(currentDate: Long): String {
    val left = (soldierPrefs.endDate - currentDate).toDouble()
    val all = (soldierPrefs.endDate - soldierPrefs.startDate).toDouble()

    return "%.6f".format((left / all) * 100)
}

fun DembelTimerView.updateCounter(time: DiffTime) {
    this.days_count_text?.text  = time.days.toString()
    this.hours_count_text?.text = time.hours.toString()
    this.minutes_count_text?.text = time.minutes.toString()
    this.seconds_count_text?.text = time.seconds.toString()
}

fun DembelTimerView.updatePassedPercentage(currentDate: Long) {
    this.percentage_text?.text = "${percentagePassed(currentDate)}%"
}

fun DembelTimerView.updateLeftPercentage(currentDate: Long) {
    this.percentage_text?.text = "${percentageLeft(currentDate)}%"
}

fun PieChart.init() {
    this.setUsePercentValues(true)
    this.description.isEnabled = false
    this.setExtraOffsets(5f, 10f, 5f, 5f)

    this.dragDecelerationFrictionCoef = 0.95f

    this.centerText = "Military"

    this.isDrawHoleEnabled = true
    this.setHoleColor(Color.WHITE)

    this.setTransparentCircleColor(Color.WHITE)
    this.setTransparentCircleAlpha(110)

    this.holeRadius = 58f
    this.transparentCircleRadius = 61f

    this.setDrawCenterText(true)

    this.rotationAngle = 0f
    // enable rotation of the this by touch
    this.isRotationEnabled = true
    this.isHighlightPerTapEnabled = true

    this.animateY(1400, Easing.EaseInOutQuad)

    val l = this.legend

    l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
    l.orientation = Legend.LegendOrientation.VERTICAL

    l.setDrawInside(false)

    l.xEntrySpace = 7f
    l.yEntrySpace = 0f
    l.yOffset = 0f
    l.xOffset = 10f

    // entry label styling
    this.setEntryLabelColor(Color.WHITE)
    //this.setEntryLabelTypeface(tfRegular)
    this.setEntryLabelTextSize(12f)
}

fun PieChart.updateData(passedPercent: Float, leftPercent: Float) {
    val entries = arrayListOf<PieEntry>()

    entries.add(PieEntry(passedPercent, "Passed", resources.getDrawable(R.drawable.happy_soldier)))
    entries.add(PieEntry(leftPercent, "Left", resources.getDrawable(R.drawable.sad_soldier)))

    val dataSet = PieDataSet(entries, "Military")
    dataSet.setDrawIcons(false)

    dataSet.sliceSpace = 3f
    dataSet.iconsOffset = MPPointF(0f, 40f)
    dataSet.selectionShift = 5f

    val colors = arrayListOf<Int>()
    colors.add(ColorTemplate.rgb("#008577"))
    colors.add(ColorTemplate.rgb("#f44336"))

    dataSet.setColors(colors)

    val data = PieData(dataSet)
    data.setValueFormatter(PercentFormatter())
    data.setValueTextSize(11f)
    data.setValueTextColor(Color.WHITE)
    //data.setValueTypeface(tfLight)

    this.data = data
    this.highlightValues(null)
    this.invalidate()
}

