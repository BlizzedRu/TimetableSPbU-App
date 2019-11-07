package ru.blizzed.timetablespbu.ui.screens.faculties

import ru.blizzed.timetablespbu.domain.entities.Faculty

sealed class Change {
    object Loading : Change()

    class Error(val error: Throwable) : Change()

    class Loaded(val faculties: List<Faculty>) : Change()
}
