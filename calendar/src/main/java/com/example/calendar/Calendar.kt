package com.example.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calendar.CalendarState.Companion.rememberCalendarState
import com.example.calendar.ui.Month
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarState: CalendarState = rememberCalendarState(),
    onSelect: (LocalDate) -> Unit = {}
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = calendarState::loadPrevious
            ) {
                Text(text = "prev")
            }

            Button(
                onClick = calendarState::loadNext
            ) {
                Text(text = "next")
            }
        }

        Month(
            month = calendarState.value,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}