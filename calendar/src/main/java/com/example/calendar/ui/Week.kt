package com.example.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.calendar.model.Week
import java.time.LocalDate

@Composable
internal fun Week(
    week: Week,
    onSelect: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        week.days.forEach { day ->
            Day(
                day = day,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onSelect(day.date) }
            )
        }
    }
}