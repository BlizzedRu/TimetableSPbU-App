package ru.blizzed.timetablespbu.domain.entities

data class Educator(
    val id: Int,
    val fullName: String,
    val employments: List<EducatorEmployment>
)