package com.example.calendar.model

import java.time.LocalDate

data class Month(
    val weeks: List<Week>
) {
    companion object {
        fun create(
            page: Int,
            weekInMonth: Int,
            daysInWeek: Int,
        ): Month {
            val currentDate = LocalDate.now().plusMonths(page.toLong())

            return Month(
                weeks = List(weekInMonth) { rowIndex ->
                    Week.create(
                        currentDate = currentDate,
                        daysInWeek = daysInWeek,
                        rowIndex = rowIndex,
                    )
                }
            )
        }
    }
}