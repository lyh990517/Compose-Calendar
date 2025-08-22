package com.example.calendar.model

import java.time.LocalDate

data class Week(
    val days: List<Day>
) {
    companion object {
        fun create(
            currentDate: LocalDate,
            daysInWeek: Int,
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