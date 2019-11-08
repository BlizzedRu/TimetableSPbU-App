package ru.blizzed.timetablespbu.ui.screens.search.faculties

import ru.blizzed.timetablespbu.domain.entities.Faculty

sealed class StateChange {
    object Loading : StateChange()

    class Error(val error: Throwable) : StateChange()

    class Loaded(val faculties: List<Faculty>) : StateChange()
}
