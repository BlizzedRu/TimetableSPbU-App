package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

sealed class ViewEvent<out Item, out SelectionItem, out Param> {

    class Load<out Item, out SelectionItem, out Param>(val param: Param) : ViewEvent<Item, SelectionItem, Param>()

    class Select<out Item, out SelectionItem, out Param>(val item: SelectionItem) : ViewEvent<Item, SelectionItem, Param>()

}
