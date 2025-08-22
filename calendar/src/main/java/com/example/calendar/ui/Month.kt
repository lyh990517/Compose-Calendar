package com.example.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calendar.LocalCalendarState

@Composable
internal fun Month(
    modifier: Modifier = Modifier,
) {
    val calendarState = LocalCalendarState.current
    val month = calendarState.value

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        month.weeks.forEach { week ->
            Week(
                week = week,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}