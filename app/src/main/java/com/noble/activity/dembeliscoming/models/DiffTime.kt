package com.noble.activity.dembeliscoming.models

data class DiffTime(val days: Long, val hours: Long, val minutes: Long, val seconds: Long)

fun DiffTime.stringify(): String {
    return "${this.days} ${this.hours} ${this.minutes} ${this.seconds}"
}