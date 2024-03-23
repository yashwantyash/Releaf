package com.releaf.releaf.models

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { Date(it).toInstant().atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.let { Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant()).time }
    }
}