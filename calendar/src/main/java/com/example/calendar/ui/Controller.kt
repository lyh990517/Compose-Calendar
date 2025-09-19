package com.example.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.LocalCalendarState

@Composable
fun Controller(
    modifier: Modifier = Modifier
) {
    val calendarState = LocalCalendarState.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable(onClick = calendarState::onPrevious),
            text = "prev",
            fontSize = 20.sp
        )

        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.clickable(onClick = calendarState::onNext),
            text = "next",
            fontSize = 20.sp
        )
    }
}
