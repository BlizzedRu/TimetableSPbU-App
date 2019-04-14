package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.blizzed.timetablespbu.domain.entities.Interactable

@Entity(tableName = "educators")
class EducatorData(
        @PrimaryKey val id: Int,
        val fullName: String,
        val employments: List<EducatorEmploymentData>,
        isFavorite: Boolean = false,
        isViewed: Boolean = false,
        lastViewTime: Long = 0L
) : Interactable(isFavorite, isViewed, lastViewTime)
