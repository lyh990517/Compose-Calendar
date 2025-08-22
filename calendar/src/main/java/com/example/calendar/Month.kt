package com.example.calendar

import java.time.LocalDate

data class Month(
    val weeks: List<Week>
) {
    companion object {
        private const val WEEKS_IN_MONTH = 5

        fun create(page: Int): Month {
            val currentDate = LocalDate.now().plusMonths(page.toLong())

            return Month(
                weeks = List(WEEKS_IN_MONTH) { rowIndex ->
                    Week.create(
                        currentDate = currentDate,
                        rowIndex = rowIndex,
                    )
                }
            )
        }
    }
}