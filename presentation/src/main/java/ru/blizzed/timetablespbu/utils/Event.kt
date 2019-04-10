package ru.blizzed.timetablespbu.utils

sealed class Event<out T>(open val data: T?) {

    class Loading<out T>(override val data: T? = null): Event<T>(data)

    class Error<out T>(val throwable: Throwable, override val data: T? = null): Event<T>(data)

    class Success<out T>(override val data: T): Event<T>(data)

    class Complete<out T>(override val data: T? = null): Event<T>(data)

}