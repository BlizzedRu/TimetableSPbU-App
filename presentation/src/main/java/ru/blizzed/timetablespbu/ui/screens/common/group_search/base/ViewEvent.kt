package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

sealed class ViewEvent {

    class Load : ViewEvent()

    data class Select<out SelectionItem>(val item: SelectionItem) : ViewEvent()

}
