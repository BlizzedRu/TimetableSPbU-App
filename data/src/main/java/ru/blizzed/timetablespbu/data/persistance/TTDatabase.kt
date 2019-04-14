package ru.blizzed.timetablespbu.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.blizzed.timetablespbu.data.persistance.dao.EducatorsDao
import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData

@Database(version = 1, entities = [
    EducatorData::class
])
@TypeConverters(Converters::class)
abstract class TTDatabase : RoomDatabase() {

    abstract val educatorsDao: EducatorsDao

}