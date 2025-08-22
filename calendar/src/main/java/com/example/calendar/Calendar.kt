package com.example.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.CalendarState.Companion.rememberCalendarState
import com.example.calendar.ui.Controller
import com.example.calendar.ui.DaysOfWeek
import com.example.calendar.ui.Month

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarState: CalendarState = rememberCalendarState(),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = calendarState.displayedDateText,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        DaysOfWeek(modifier = Modifier.fillMaxWidth())

        Month(
            calendarState = calendarState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Controller(
            calendarState = calendarState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}