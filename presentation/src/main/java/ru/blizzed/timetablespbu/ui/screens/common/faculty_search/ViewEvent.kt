package ru.blizzed.timetablespbu.ui.screens.common.faculty_search

sealed class ViewEvent {

    object Load : ViewEvent()

    class Search(val query: String) : ViewEvent()

}
