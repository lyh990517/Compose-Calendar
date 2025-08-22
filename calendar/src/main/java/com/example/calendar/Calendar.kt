package com.example.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendar.CalendarState.Companion.rememberCalendarState
import com.example.calendar.ui.Controller
import com.example.calendar.ui.DaysOfWeek
import com.example.calendar.ui.DisplayedDate
import com.example.calendar.ui.Month

internal val LocalCalendarState = staticCompositionLocalOf<CalendarState> { error("no state!") }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarState: CalendarState = rememberCalendarState(),
) {
    CompositionLocalProvider(
        LocalCalendarState provides calendarState
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplayedDate()

            Spacer(modifier = Modifier.height(20.dp))

            DaysOfWeek(modifier = Modifier.fillMaxWidth())

            Month(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Controller(
                onPrevious = calendarState::onPrevious,
                onNext = calendarState::onNext,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}