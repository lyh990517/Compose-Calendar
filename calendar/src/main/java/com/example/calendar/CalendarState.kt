package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

@Stable
class CalendarState {
    var page by mutableIntStateOf(0)

    val value
        @Composable get() = produceState(
            initialValue = Month(weeks = emptyList()),
            key1 = page
        ) {
            value = Month.create(page)
        }

    fun loadNext() {
        page++
    }

    fun loadPrevious() {
        page--
    }

    companion object {
        @Composable
        fun rememberCalendarState() = remember { CalendarState() }
    }
}

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

data class Day(
    val day: LocalDate
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
                day = startOfWeek.plusDays((rowIndex * daysInWeek + columnIndex).toLong())
            )
        }
    }
}