package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classrooms")
data class ClassroomData(
        @PrimaryKey val oid: String,
        val name: String,
        val capacity: Int,
        val additionalInfo: String?
)
