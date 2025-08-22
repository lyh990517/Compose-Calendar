package com.example.calendar.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.LocalCalendarState
import com.example.calendar.extension.contains
import com.example.calendar.model.Day

@Composable
internal fun Day(
    day: Day,
    modifier: Modifier = Modifier,
) {
    val calendarState = LocalCalendarState.current
    val textColor = remember(calendarState.currentDate) {
        if (calendarState.currentDate.contains(day)) {
            Color.Black
        } else {
            Color.LightGray
        }
    }

    Box(
        modifier = modifier.size(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = textColor
        )
    }
}
