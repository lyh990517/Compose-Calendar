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
    val weeks: List<Week> = emptyList()
) {
    companion object {
        private const val WEEKS_IN_MONTH = 5

        fun create(page: Int): Month {
            val currentDate = LocalDate.now().plusMonths(page.toLong())

            return Month(
                weeks = List(WEEKS_IN_MONTH) { week ->
                    Week.create(
                        currentDate = currentDate,
                        week = week,
                    )
                }
            )
        }
    }
}

data class Week(
    val days: List<LocalDate>
) {
    companion object {
        private const val DAYS_IN_WEEK = 7

        fun create(
            currentDate: LocalDate,
            week: Int,
        ): Week = Week(
            days = List(DAYS_IN_WEEK) { day ->
                val firstDayOfMonth = YearMonth.of(currentDate.year, currentDate.monthValue).atDay(1)
                val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

                startOfWeek.plusDays((week * DAYS_IN_WEEK + day).toLong())
            }
        )
    }
}