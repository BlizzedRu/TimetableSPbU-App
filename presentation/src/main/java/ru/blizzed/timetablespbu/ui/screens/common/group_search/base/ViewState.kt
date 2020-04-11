package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

data class ViewState<Item>(
  val isIdle: Boolean,
  val isLoading: Boolean = false,
  val isError: Boolean = false,
  val items: List<Item>? = null
)
