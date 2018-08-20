package com.boxfox.android.myrelationshipsapplication.util

import java.util.*

object AgeCalHelper {
    fun toAge(year: Int): Int {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return currentYear - year
    }

    fun toYear(age: Int): Int {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return currentYear - age
    }
}