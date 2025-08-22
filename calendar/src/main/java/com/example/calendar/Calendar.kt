package com.example.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calendar.CalendarState.Companion.rememberCalendarState
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    onSelect: (LocalDate) -> Unit
) {
    val calendarState = rememberCalendarState()
    val value by calendarState.value

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { calendarState.loadPrevious() }
            ) {
                Text("prev")
            }

            Button(
                onClick = { calendarState.loadNext() }
            ) {
                Text("next")
            }
        }

        Text("$value")
    }
}
