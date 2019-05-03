package ru.blizzed.timetablespbu.extensions

import ru.blizzed.timetablespbu.domain.entities.NameComponents

private const val INITIALS_SEPARATOR = "."

fun NameComponents.getShortInitials(): String = listOfNotNull(firstName?.firstUpperLetter(), patronymic?.firstUpperLetter()).joinToString(INITIALS_SEPARATOR)

private fun CharSequence.firstUpperLetter() = first().toUpperCase()
