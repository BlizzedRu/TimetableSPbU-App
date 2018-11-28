package ru.blizzed.timetablespbu.domain.entities

data class EducatorEntity(
    val id: Int,
    val fullName: String,
    val employments: List<EducatorEmployment>
)