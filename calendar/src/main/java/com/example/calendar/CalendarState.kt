package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.calendar.model.Day
import com.example.calendar.model.Month
import com.example.calendar.model.Week
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Stable
class CalendarState(
    private val weekInMonth: Int,
    private val daysInWeek: Int,
) {
    val displayedDateText: String get() = LocalDate.now().plusMonths((page - (Int.MAX_VALUE / 2)).toLong()).format(DateTimeFormatter.ofPattern("yyyy.MM"))
    var page by mutableIntStateOf(Int.MAX_VALUE / 2)
        private set
    var selectedDate by mutableStateOf<LocalDate?>(null)
        private set

    fun getMonth(offset: Int) = createMonth(
        page = offset,
        weekInMonth = weekInMonth,
        daysInWeek = daysInWeek
    )

    fun onSelect(localDate: LocalDate) {
        selectedDate = localDate
    }

    fun onNext() {
        page++
    }

    fun onPrevious() {
        page--
    }

    private fun createMonth(
        page: Int,
        weekInMonth: Int,
        daysInWeek: Int,
    ): Month {
        val currentDate = LocalDate.now().plusMonths(page.toLong())

        return Month(
            weeks = List(weekInMonth) { rowIndex ->
                createWeek(
                    currentDate = currentDate,
                    daysInWeek = daysInWeek,
                    rowIndex = rowIndex,
                )
            }
        )
    }

    private fun createWeek(
        currentDate: LocalDate,
        daysInWeek: Int,
        rowIndex: Int,
    ): Week = Week(
        days = List(daysInWeek) { columnIndex ->
            createDay(
                currentDate = currentDate,
                daysInWeek = daysInWeek,
                rowIndex = rowIndex,
                columnIndex = columnIndex
            )
        }
    )

    private fun createDay(
        currentDate: LocalDate,
        daysInWeek: Int,
        rowIndex: Int,
        columnIndex: Int,
    ): Day {
        val firstDayOfMonth = YearMonth.of(currentDate.year, currentDate.monthValue).atDay(1)
        val startOfWeek = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        return Day(
            date = startOfWeek.plusDays((rowIndex * daysInWeek + columnIndex).toLong())
        )
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
