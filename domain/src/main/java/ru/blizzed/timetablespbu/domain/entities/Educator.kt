package ru.blizzed.timetablespbu.domain.entities

import java.util.Objects

class Educator(
    val id: Int,
    val fullName: String,
    val employments: List<EducatorEmployment>,
    var circleColor: Int = NO_COLOR,
    isFavorite: Boolean = false,
    isViewed: Boolean = false,
    lastInteractionTime: Long = 0L
) : Interactable(isFavorite, isViewed, lastInteractionTime) {

    companion object {
        const val NO_COLOR = 0
        private const val FULL_NAME_PARTS_SEPARATOR = " "
    }

    /**
     * I believe that any educator name is build with this format %lastName firstName? patronymic? ..?%
     */
    fun getNameComponents(): NameComponents = fullName.split(FULL_NAME_PARTS_SEPARATOR)
        .run {
            NameComponents(
                lastName = first(),
                firstName = takeIf { size > 1 }?.get(1),
                patronymic = takeIf { size > 2 }?.get(2)
            )
        }

    override fun equals(other: Any?): Boolean {
        if (other == null || other.javaClass != javaClass) return false
        return (other as Educator).id == id
    }

    override fun hashCode(): Int = Objects.hashCode(id)

}
