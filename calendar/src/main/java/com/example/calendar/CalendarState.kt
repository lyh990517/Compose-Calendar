package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calendar.model.Month

@Stable
class CalendarState(
    private val weekInMonth: Int,
    private val daysInWeek: Int,
) {
    val value
        @Composable get() = remember(page) {
            Month.create(
                page = page,
                weekInMonth = weekInMonth,
                daysInWeek = daysInWeek
            )
        }
    var page by mutableIntStateOf(0)
        private set

    fun loadNext() {
        page++
    }

    fun loadPrevious() {
        page--
    }

    companion object {
        private const val WEEKS_IN_MONTH = 5
        private const val DAYS_IN_WEEK = 7

        @Composable
        fun rememberCalendarState(
            weekInMonth: Int = WEEKS_IN_MONTH,
            daysInWeek: Int = DAYS_IN_WEEK,
        ) = remember {
            CalendarState(
                weekInMonth = weekInMonth,
                daysInWeek = daysInWeek
            )
        }
    }
}