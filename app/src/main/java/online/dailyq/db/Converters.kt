package online.dailyq.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(value: Long?): Date? {
        // Long -> Date
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        // Date -> Long
        return value?.time
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        // String -> LocalDate
        return if (value == null) null else LocalDate.parse(value)
    }

    @TypeConverter
    fun toString(value: LocalDate?): String? {
        // LocalDate -> String
        return value?.toString()
    }
}