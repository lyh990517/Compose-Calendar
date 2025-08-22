package com.example.compose_calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendar.Calendar
import com.example.calendar.CalendarState.Companion.rememberCalendarState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val calendarState = rememberCalendarState()

            LaunchedEffect(calendarState.selectedDate) {
                calendarState.selectedDate?.let { date ->
                    println("Selected date: $date")
                }
            }

            Calendar(
                calendarState = calendarState,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}
