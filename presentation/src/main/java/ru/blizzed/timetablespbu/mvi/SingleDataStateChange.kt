package ru.blizzed.timetablespbu.mvi

sealed class SingleDataStateChange<Data> {
    class Loading<Data>(val lastData: Data? = null): SingleDataStateChange<Data>()
    class Loaded<Data>(val data: Data): SingleDataStateChange<Data>()
    class Error<Data>(val throwable: Throwable, val lastData: Data? = null): SingleDataStateChange<Data>()
}
