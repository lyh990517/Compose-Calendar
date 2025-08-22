package com.example.calendar.model

import java.time.LocalDate

data class Week(
    val days: List<Day>
) {
    companion object {
        fun create(
            daysInWeek: Int,
            currentDate: LocalDate,
            rowIndex: Int,
        ): Week = Week(
            days = List(daysInWeek) { columnIndex ->
                Day.create(
                    currentDate = currentDate,
                    daysInWeek = daysInWeek,
                    rowIndex = rowIndex,
                    columnIndex = columnIndex
                )
            }
        )
    }
}