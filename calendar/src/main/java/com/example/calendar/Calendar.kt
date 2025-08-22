package com.example.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import com.example.calendar.CalendarState.Companion.rememberCalendarState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    pageCount: Int = 12,
    onSelect: (CalendarDate) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val calendarState = rememberCalendarState()
    val value by calendarState.value
    val pagerState = rememberPagerState { pageCount }

    Column(
        modifier
            .background(Color.White)
    ) {
        HorizontalPager(
            modifier = modifier
                .weight(1f)
                .testTag("pager"), state = pagerState
        ) { page ->

        }
    }
}
