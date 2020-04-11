package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

sealed class StateChange<Item> {

    class Loading<Item> : StateChange<Item>()

    data class Error<Item>(val throwable: Throwable) : StateChange<Item>()

    data class Loaded<Item>(val items: List<Item>) : StateChange<Item>()

}
