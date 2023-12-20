package com.example.studysmart.util

import androidx.compose.ui.graphics.Color
import com.example.studysmart.ui.theme.Orange

enum class Priority(val title:String,val color : Color,val value :Int) {
    LOw(title = "Low", Color.Green,0),
    MEDIUM(title = "Medium", Orange,1),
    HIGH(title = "High", Color.Red,2);

    companion object {
        fun fromInt(value:Int) = values().firstOrNull { it.value==value } ?: MEDIUM
    }
}