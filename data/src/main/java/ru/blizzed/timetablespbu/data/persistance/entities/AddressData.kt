package ru.blizzed.timetablespbu.data.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressData(
        @PrimaryKey val oid: String,
        val name: String
)
