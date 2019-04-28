package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "educators")
data class EducatorData(
        @PrimaryKey val id: Int,
        val fullName: String,
        val employments: List<EducatorEmploymentData>,
        val circleColor: Int,
        val isFavorite: Boolean = false,
        val isViewed: Boolean = false,
        val lastInteractionTime: Long = 0L
)
