package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

private const val WEEKS_IN_MONTH = 5
private const val DAYS_IN_WEEK = 7
private const val DEFAULT_PAGE_COUNT = 2

@Stable
class CalendarState {
    val value
        @Composable get() = produceState(CalendarStateData()) {
            value = CalendarStateData(
                calendar = CalendarPageGenerator.generate(DEFAULT_PAGE_COUNT),
            )
        }

    companion object {
        @Composable
        fun rememberCalendarState() = remember { CalendarState() }
    }
}

data class CalendarStateData(
    val calendar: List<Month> = emptyList()
)

data class Month(
    val weeks: List<Week>
)

data class Week(
    val days: List<LocalDate>
)

object CalendarPageGenerator {
    fun generate(pageCount: Int): List<Month> {
        val currentDate = LocalDate.now()
        return List(pageCount) { pageIndex ->
            val targetDate = currentDate.plusMonths(pageIndex.toLong())
            Month(
                weeks = MonthGenerator.createMonth(targetDate.year, targetDate.monthValue)
            )
        }
    }
}

object MonthGenerator {
    fun createMonth(year: Int, month: Int): List<Week> {
        val firstDayOfMonth = YearMonth.of(year, month).atDay(1)
        val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        return List(WEEKS_IN_MONTH) { week ->
            Week(
                days = List(DAYS_IN_WEEK) { day ->
                    val currentDate = startOfWeek.plusDays((week * DAYS_IN_WEEK + day).toLong())
                    if (shouldIncludeDate(currentDate, month)) {
                        currentDate
                    } else {
                        LocalDate.of(1970, 1, 1) // placeholder for empty days
                    }
                }
            )
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
}