package com.bchmsl.homework14_customarchitecture.extensions

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String?.toDate():String{
    val date = this?.split(" ")?.get(0)
    val formattedDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(
            DateTimeFormatter.ofPattern("MMM d, YYYY")
        )
    } else {
        date
    }
    return formattedDate.toString()
}