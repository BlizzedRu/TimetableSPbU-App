package ru.blizzed.timetablespbu.domain.entities

data class StudentInfo(
        val facultiesAffiliation: List<Faculty>,
        val groupsAffiliation: List<String>     // TODO
)
