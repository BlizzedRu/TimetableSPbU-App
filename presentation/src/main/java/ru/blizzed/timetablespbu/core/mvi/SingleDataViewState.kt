package ru.blizzed.timetablespbu.core.mvi

data class SingleDataViewState<Data>(
        val isLoading: Boolean,
        val isError: Boolean = false,
        val data: Data? = null
)
