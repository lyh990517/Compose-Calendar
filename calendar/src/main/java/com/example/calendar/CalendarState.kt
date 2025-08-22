package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Calendar
import java.util.Locale

@Stable
class CalendarState {
    val value
        @Composable get() = produceState<State>(State()) {
            value = State(
                calendar = CalendarPage.make(2),
                dateIndex = DateIndex()
            )
        }

    data class State(
        val calendar: List<CalendarPage>,
        val dateIndex: DateIndex
    ) {
        constructor() : this(
            calendar = emptyList(),
            dateIndex = DateIndex()
        )
    }

    data class CalendarPage(
        val year: Int,
        val month: Int,
        val calendar: List<List<CalendarDate?>>
    ) {
        companion object {
            private val calendar = Calendar.getInstance()

            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH) + 1

            fun make(pageCount: Int) = List(pageCount) { pageIndex ->
                val totalMonth = currentMonth + pageIndex
                val adjustedYear = currentYear + (totalMonth - 1) / 12
                val adjustedMonth = if (totalMonth % 12 == 0) 12 else totalMonth % 12

                CalendarPage(
                    year = adjustedYear,
                    month = adjustedMonth,
                    calendar = makeMonth(adjustedYear, adjustedMonth)
                )
            }

            fun makeMonth(year: Int, month: Int): List<List<CalendarDate?>> {
                val daysList = MutableList(5) { MutableList<CalendarDate?>(7) { null } }

                val firstDayOfMonth = YearMonth.of(year, month).atDay(1)
                var currentDay =
                    firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

                for (week in 0 until 5) {
                    for (dayOfWeek in 0 until 7) {
                        val adjustedYear =
                            if (month == 1 && currentDay.monthValue == 12) year - 1 else if (month == 12 && currentDay.monthValue == 1) year + 1 else year
                        if (currentDay.monthValue == month || currentDay.isBefore(firstDayOfMonth) || currentDay.monthValue == (month % 12) + 1) {
                            val dayName =
                                currentDay.dayOfWeek.getDisplayName(
                                    TextStyle.SHORT,
                                    Locale.getDefault()
                                )
                            daysList[week][dayOfWeek] =
                                CalendarDate(
                                    adjustedYear,
                                    currentDay.monthValue,
                                    currentDay.dayOfMonth,
                                    dayName
                                )
                        }
                        currentDay = currentDay.plusDays(1)
                    }
                }

                return daysList
            }
        }
    }

    companion object {
        @Composable
        fun rememberCalendarState() = remember { CalendarState() }
    }
}

data class CalendarDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: String
)

data class DateIndex(
    val month: Int,
    val week: Int,
    val day: Int
) {
    constructor() : this(
        month = NOT_SELECTED,
        week = NOT_SELECTED,
        day = NOT_SELECTED
    )

    companion object {
        const val NOT_SELECTED = -1
    }
}
