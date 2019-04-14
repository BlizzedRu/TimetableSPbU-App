package ru.blizzed.timetablespbu.data.persistance

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.blizzed.timetablespbu.data.persistance.entities.EducatorEmploymentData

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun convertEducatorEmploymentTo(item: List<EducatorEmploymentData>): String = toJson(item)

    @TypeConverter
    fun convertEducatorEmploymentFrom(stringedItem: String): List<EducatorEmploymentData> = fromJson(stringedItem)

    private inline fun <reified T> toJson(item: T): String = gson.toJson(item, object : TypeToken<T>() {}.type)

    private inline fun <reified T> fromJson(item: String): T = gson.fromJson(item, object : TypeToken<T>() {}.type)

}
