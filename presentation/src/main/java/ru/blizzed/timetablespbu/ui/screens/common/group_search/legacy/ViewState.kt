package ru.blizzed.timetablespbu.ui.screens.common.group_search.legacy

data class ViewState(
        val isIdle: Boolean,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val step: Step
)
