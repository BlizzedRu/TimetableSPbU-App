package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity

@Entity(tableName = "educators")
data class EducatorData(
    val id: Int,
    val fullName: String,
    val employments: List<EducatorEmploymentData>
)