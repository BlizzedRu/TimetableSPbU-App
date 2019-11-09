package ru.blizzed.timetablespbu.mvi

data class SingleDataViewState<Data>(
        val isLoading: Boolean,
        val isError: Boolean = false,
        val data: Data? = null
)
