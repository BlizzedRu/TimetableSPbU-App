package ru.blizzed.timetablespbu.ui.screens.search.faculties

import ru.blizzed.timetablespbu.domain.entities.Faculty

sealed class ViewEvent {

    object Load : ViewEvent()

    class Search(val query: String) : ViewEvent()

    class Select(val faculty: Faculty) : ViewEvent()

}
