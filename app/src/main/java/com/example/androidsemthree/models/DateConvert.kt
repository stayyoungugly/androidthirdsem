package com.example.androidsemthree.models

import androidx.room.TypeConverter
import java.util.*

class DateConvert {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }
}
