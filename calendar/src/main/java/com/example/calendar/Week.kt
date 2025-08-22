package com.example.calendar

import java.time.LocalDate

data class Week(
    val days: List<Day>
) {
    companion object {
        private const val DAYS_IN_WEEK = 7

        fun create(
            currentDate: LocalDate,
            rowIndex: Int,
        ): Week = Week(
            days = List(DAYS_IN_WEEK) { columnIndex ->
                Day.create(
                    currentDate = currentDate,
                    daysInWeek = DAYS_IN_WEEK,
                    rowIndex = rowIndex,
                    columnIndex = columnIndex
                )
            }
        )
    }
}