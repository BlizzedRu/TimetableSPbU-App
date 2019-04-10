package ru.blizzed.timetablespbu.domain.datasources

import ru.blizzed.timetablespbu.domain.entities.Faculty

interface FacultiesDataSource {
    fun getFaculties(): List<Faculty>
}