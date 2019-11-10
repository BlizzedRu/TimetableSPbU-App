package ru.blizzed.timetablespbu.ui.screens.common.faculty_selection

import ru.blizzed.timetablespbu.domain.entities.Faculty

data class ViewState(
        val isIdle: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val error: Throwable? = null,
        val faculties: List<Faculty> = listOf()
)
