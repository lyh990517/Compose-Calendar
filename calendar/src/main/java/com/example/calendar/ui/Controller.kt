package com.example.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.LocalCalendarState
import kotlinx.coroutines.launch

@Composable
fun Controller(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val calendarState = LocalCalendarState.current
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    calendarState.onPrevious()
                }
            },
            text = "prev",
            fontSize = 20.sp
        )

        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    calendarState.onNext()
                }
            },
            text = "next",
            fontSize = 20.sp
        )
    }
}
