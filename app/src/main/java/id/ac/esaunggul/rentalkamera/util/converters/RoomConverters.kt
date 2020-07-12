package id.ac.esaunggul.rentalkamera.util.converters

import androidx.room.TypeConverter
import java.util.Date

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}