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
            initialValue = Month(),
            key1 = page
        ) {
            value = Month.generate(page)
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
    val weeks: List<Week> = emptyList()
) {
    companion object {
        fun generate(page: Int): Month {
            val currentDate = LocalDate.now().plusMonths(page.toLong())

            return Month(
                weeks = Week.createWeeks(currentDate.year, currentDate.monthValue)
            )
        }
    }
}

data class Week(
    val days: List<LocalDate>
) {
    companion object {
        private const val WEEKS_IN_MONTH = 5
        private const val DAYS_IN_WEEK = 7

        fun createWeeks(year: Int, month: Int): List<Week> {
            val firstDayOfMonth = YearMonth.of(year, month).atDay(1)
            val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

            return List(WEEKS_IN_MONTH) { week ->
                Week(
                    days = List(DAYS_IN_WEEK) { day ->
                        val currentDate = startOfWeek.plusDays((week * DAYS_IN_WEEK + day).toLong())

                        if (shouldIncludeDate(date = currentDate, targetMonth = month)) {
                            currentDate
                        } else {
                            LocalDate.of(1970, 1, 1)
                        }
                    }
                )
            }
        }

        private fun shouldIncludeDate(
            date: LocalDate,
            targetMonth: Int
        ): Boolean {
            return when (date.monthValue) {
                targetMonth -> true
                targetMonth - 1, targetMonth + 1 -> true
                12 -> targetMonth == 1
                1 -> targetMonth == 12
                else -> false
            }
        }
    }
}