package com.moracoding.firebaseexp.presentation.utils

import android.icu.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDateString(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("yyyy-MMM-dd hh:mm", Locale.getDefault())
    return dateFormat.format(date)
}