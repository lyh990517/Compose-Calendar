package com.example.calendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.calendar.model.Day
import com.example.calendar.model.Month
import com.example.calendar.model.Week
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Stable
class CalendarState(
    private val coroutineScope: CoroutineScope,
    private val weekInMonth: Int,
    private val daysInWeek: Int,
) {
    val pagerState = PagerState(
        currentPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )
    var selectedDate by mutableStateOf<LocalDate?>(null)
        private set

    fun getPage(page: Int) = createMonth(
        page = page - Int.MAX_VALUE / 2,
        weekInMonth = weekInMonth,
        daysInWeek = daysInWeek
    )

    fun getDisplayedDateText() = LocalDate
        .now()
        .plusMonths((pagerState.settledPage - (Int.MAX_VALUE / 2)).toLong())
        .format(DateTimeFormatter.ofPattern("yyyy.MM")) ?: ""

    fun onSelect(localDate: LocalDate) {
        selectedDate = localDate
    }

    fun onNext() {
        with(pagerState) {
            coroutineScope.launch {
                animateScrollToPage(settledPage + 1)
            }
        }
    }

    fun onPrevious() = with(pagerState) {
        coroutineScope.launch {
            animateScrollToPage(settledPage - 1)
        }
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
        ): CalendarState {
            val state = CalendarState(
                coroutineScope = rememberCoroutineScope(),
                weekInMonth = weekInMonth,
                daysInWeek = daysInWeek
            )

            return remember { state }
        }
    }
}
