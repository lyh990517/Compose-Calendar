package com.example.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calendar.CalendarState

@Composable
internal fun Month(
    calendarState: CalendarState,
    modifier: Modifier = Modifier,
) {
    val month = calendarState.value

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        month.weeks.forEach { week ->
            Week(
                week = week,
                onSelect = calendarState::onSelect,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}