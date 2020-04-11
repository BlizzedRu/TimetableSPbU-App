package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

sealed class StateChange<out Item, out SelectionItem> {

    class Loading<out Item, out SelectionItem> : StateChange<Item, SelectionItem>()

    class Error<out Item, out SelectionItem>(val throwable: Throwable) : StateChange<Item, SelectionItem>()

    class Loaded<out Item, out SelectionItem>(val items: List<Item>) : StateChange<Item, SelectionItem>()

    class Selected<out Item, out SelectionItem>(val item: SelectionItem) : StateChange<Item, SelectionItem>()

}
