package com.example.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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