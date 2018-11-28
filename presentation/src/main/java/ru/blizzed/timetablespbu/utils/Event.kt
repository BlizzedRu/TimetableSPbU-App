package ru.blizzed.timetablespbu.utils

sealed class Event<out T>(open val data: T?) {

    class Loading<out T>: Event<T>(null)

    class Error<out T>(val throwable: Throwable): Event<T>(null)

    class Success<out T>(override val data: T): Event<T>(data)

    class Completed<out T>: Event<T>(null)

}