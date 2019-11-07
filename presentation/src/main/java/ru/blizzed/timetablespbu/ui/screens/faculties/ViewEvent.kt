package ru.blizzed.timetablespbu.ui.screens.faculties

import ru.blizzed.timetablespbu.domain.entities.Faculty

sealed class ViewEvent {

    object Load : ViewEvent()

    class Search(val query: String) : ViewEvent()

    class Select(val faculty: Faculty) : ViewEvent()

}
