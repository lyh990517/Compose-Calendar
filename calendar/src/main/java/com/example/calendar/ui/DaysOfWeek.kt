package com.example.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun DaysOfWeek(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val locale = remember { configuration.locales.get(0) ?: Locale.getDefault() }
    val daysOfWeek = remember {
        val entries = DayOfWeek.entries

        entries.drop(6) + entries.take(6)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day.getDisplayName(TextStyle.SHORT, locale),
            )
        }
    }
}