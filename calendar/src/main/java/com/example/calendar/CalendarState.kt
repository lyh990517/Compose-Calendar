package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

private const val WEEKS_IN_MONTH = 5
private const val DAYS_IN_WEEK = 7
private const val DEFAULT_PAGE_COUNT = 2

@Stable
class CalendarState {
    val value
        @Composable get() = produceState(CalendarStateData()) {
            value = CalendarStateData(
                calendar = CalendarPageGenerator.generate(DEFAULT_PAGE_COUNT),
                dateIndex = DateIndex.none()
            )
        }

    companion object {
        @Composable
        fun rememberCalendarState() = remember { CalendarState() }
    }
}

data class CalendarStateData(
    val calendar: List<CalendarPage> = emptyList(),
    val dateIndex: DateIndex = DateIndex.none()
)

data class CalendarPage(
    val year: Int,
    val month: Int,
    val days: List<List<CalendarDate?>>
)

object CalendarPageGenerator {
    fun generate(pageCount: Int): List<CalendarPage> {
        val currentDate = LocalDate.now()
        return List(pageCount) { pageIndex ->
            val targetDate = currentDate.plusMonths(pageIndex.toLong())
            CalendarPage(
                year = targetDate.year,
                month = targetDate.monthValue,
                days = MonthGenerator.createMonth(targetDate.year, targetDate.monthValue)
            )
        }
    }
}

object MonthGenerator {
    fun createMonth(year: Int, month: Int): List<List<CalendarDate?>> {
        val monthGrid = createEmptyGrid()
        val firstDayOfMonth = YearMonth.of(year, month).atDay(1)
        val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        fillMonthGrid(monthGrid, startOfWeek, month)
        return monthGrid
    }

    private fun createEmptyGrid(): MutableList<MutableList<CalendarDate?>> {
        return MutableList(WEEKS_IN_MONTH) {
            MutableList(DAYS_IN_WEEK) { null }
        }
    }

    private fun fillMonthGrid(
        grid: MutableList<MutableList<CalendarDate?>>,
        startDate: LocalDate,
        targetMonth: Int
    ) {
        var currentDate = startDate

        for (week in 0 until WEEKS_IN_MONTH) {
            for (day in 0 until DAYS_IN_WEEK) {
                if (shouldIncludeDate(currentDate, targetMonth)) {
                    grid[week][day] = currentDate.toCalendarDate()
                }
                currentDate = currentDate.plusDays(1)
            }
        }
    }

    private fun shouldIncludeDate(date: LocalDate, targetMonth: Int): Boolean {
        return when (date.monthValue) {
            targetMonth -> true
            targetMonth - 1, targetMonth + 1 -> true
            12 -> targetMonth == 1
            1 -> targetMonth == 12
            else -> false
        }
    }

    private fun LocalDate.toCalendarDate(): CalendarDate {
        return CalendarDate(
            year = year,
            month = monthValue,
            day = dayOfMonth,
            dayOfWeek = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        )
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
    val isSelected: Boolean
        get() = month != NOT_SELECTED && week != NOT_SELECTED && day != NOT_SELECTED

    companion object {
        private const val NOT_SELECTED = -1

        fun none() = DateIndex(NOT_SELECTED, NOT_SELECTED, NOT_SELECTED)

        fun of(month: Int, week: Int, day: Int) = DateIndex(month, week, day)
    }
}
