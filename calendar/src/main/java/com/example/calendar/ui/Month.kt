package com.example.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calendar.LocalCalendarState
import com.example.calendar.model.Month

@Composable
internal fun Month(
    month: Month,
    modifier: Modifier = Modifier,
) {
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
