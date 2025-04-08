package com.silentdev.whetherkmm.core.util

import kotlinx.datetime.LocalDate

fun String.toFormattedDate(): String {
    return try {
        val date = LocalDate.parse(this)
        val dayOfWeek = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = date.dayOfMonth.toString().padStart(2, '0')

        "$dayOfWeek, $day $month"
    } catch (e: Exception) {
        this // fallback to original if parsing fails
    }
}

