package ru.blizzed.timetablespbu.domain.entities

import java.util.Objects

class Educator(
        val id: Int,
        val fullName: String,
        val employments: List<EducatorEmployment>,
        isFavorite: Boolean = false,
        isViewed: Boolean = false,
        lastViewTime: Long = 0L
): Interactable(isFavorite, isViewed, lastViewTime) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other.javaClass != javaClass) return false
        return (other as Educator).id == id
    }

    override fun hashCode(): Int = Objects.hashCode(id)

}
