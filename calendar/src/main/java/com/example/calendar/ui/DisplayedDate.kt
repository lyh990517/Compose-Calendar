package com.example.calendar.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.calendar.LocalCalendarState

/**
 * To ensure that the state is read only from the corresponding Composable, I extracted the Text into the corresponding Composable function.
 *
 * This is due to smart recomposition.
 *
 * If you're curious, try leaving the text as is, without the corresponding function, and inspect it with the Layout Inspector.
 */

@Composable
fun DisplayedDate(
    modifier: Modifier = Modifier
) {
    val calendarState = LocalCalendarState.current

    Text(
        modifier = modifier,
        text = calendarState.getDisplayedDateText(),
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )
}
