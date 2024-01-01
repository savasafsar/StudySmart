package com.example.studysmart.util

import androidx.compose.ui.graphics.Color
import com.example.studysmart.ui.theme.Orange
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class Priority(val title:String,val color : Color,val value :Int) {
    LOw(title = "Low", Color.Green,0),
    MEDIUM(title = "Medium", Orange,1),
    HIGH(title = "High", Color.Red,2);

    companion object {
        fun fromInt(value:Int) = values().firstOrNull { it.value==value } ?: MEDIUM
    }
}
fun Long?.changeMillisToDateString(): String {
    val date: LocalDate = this?.let {
        Instant
            .ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    } ?: LocalDate.now()
    return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
}
fun Long.toHours() : Float {
    val hours = this.toFloat() / 3600f
    return "%.2f".format(hours).toFloat()
}