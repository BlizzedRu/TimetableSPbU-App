package ru.blizzed.timetablespbu.domain.entities

data class Classroom(
        val oid: String,
        val name: String,
        val capacity: Int,
        val additionalInfo: String?  // Sometimes contains useful info such as department affiliation
)
