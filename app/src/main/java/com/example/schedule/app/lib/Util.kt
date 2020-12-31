package com.example.schedule.app.lib

import android.app.AlertDialog
import android.content.Context
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toDateString() = this.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))

fun DayOfWeek.toFormatString() = when (this) {
    DayOfWeek.MONDAY -> "Понеділок"
    DayOfWeek.TUESDAY -> "Вівторок"
    DayOfWeek.WEDNESDAY -> "Середа"
    DayOfWeek.THURSDAY -> "Четвер"
    DayOfWeek.FRIDAY -> "П’ятниця"
    DayOfWeek.SATURDAY -> "Субота"
    DayOfWeek.SUNDAY -> "Неділя"
}

fun showMessageDialog(context: Context, message: String) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton("Ок") { dialog, _ ->
            dialog.cancel()
        }.show()
}
