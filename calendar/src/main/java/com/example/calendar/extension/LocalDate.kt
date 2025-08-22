package com.example.calendar.extension

import com.example.calendar.model.Day
import java.time.LocalDate

internal fun LocalDate.contains(day: Day): Boolean {
    val date = day.date

    return date.month == month && date.year == year
}
