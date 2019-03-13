package com.payfazz.hackernews.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.payfazz.hackernews.data.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

val Context.database: SQLiteOpenHelper
    get() = SQLiteOpenHelper.getInstance(applicationContext)

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Long.toReadableDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale("id", "ID"))
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}