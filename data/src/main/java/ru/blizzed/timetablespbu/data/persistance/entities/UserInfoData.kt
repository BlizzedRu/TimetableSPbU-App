package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfoData(
        @PrimaryKey(autoGenerate = true)
        val id: Int = -1
        // TODO user type
)
