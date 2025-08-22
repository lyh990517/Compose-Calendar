package com.example.calendar.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

data class Day(
    val date: LocalDate
) {
    companion object {
        fun create(
            currentDate: LocalDate,
            daysInWeek: Int,
            rowIndex: Int,
            columnIndex: Int,
        ): Day {
            val firstDayOfMonth = YearMonth.of(currentDate.year, currentDate.monthValue).atDay(1)
            val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

            return Day(
                date = startOfWeek.plusDays((rowIndex * daysInWeek + columnIndex).toLong())
            )
        }
    }
}