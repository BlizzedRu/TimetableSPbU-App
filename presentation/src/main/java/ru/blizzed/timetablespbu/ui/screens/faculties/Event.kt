package ru.blizzed.timetablespbu.ui.screens.faculties

import ru.blizzed.timetablespbu.domain.entities.Faculty

sealed class Event {

    object Load : Event()

    class Search(val query: String) : Event()

    class Select(val faculty: Faculty) : Event()

}
