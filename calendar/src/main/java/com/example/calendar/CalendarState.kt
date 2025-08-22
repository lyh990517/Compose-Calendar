package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calendar.model.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Stable
class CalendarState(
    private val weekInMonth: Int,
    private val daysInWeek: Int,
) {
    val value
        get() = Month.create(
            page = page,
            weekInMonth = weekInMonth,
            daysInWeek = daysInWeek
        )
    val currentDate: LocalDate get() = LocalDate.now().plusMonths(page.toLong())
    val displayedDateText: String get() = currentDate.format(DateTimeFormatter.ofPattern("yyyy.MM"))
    var page by mutableIntStateOf(0)
        private set
    var selectedDate by mutableStateOf<LocalDate?>(null)
        private set

    fun onSelect(localDate: LocalDate) {
        selectedDate = localDate
    }

    fun onNext() {
        page++
    }

    fun onPrevious() {
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