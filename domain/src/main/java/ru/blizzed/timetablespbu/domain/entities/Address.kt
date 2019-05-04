package ru.blizzed.timetablespbu.domain.entities

class Address(
        val oid: String,
        val name: String,
        isFavorite: Boolean = false,
        isViewed: Boolean = false,
        lastInteractionTime: Long = 0L
) : Interactable(isFavorite, isViewed, lastInteractionTime)
