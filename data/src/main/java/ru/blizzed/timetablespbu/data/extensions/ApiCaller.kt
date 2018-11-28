package ru.blizzed.timetablespbu.data.extensions

import io.reactivex.Single
import ru.blizzed.timetablespbulib.ApiCaller

fun <T> ApiCaller<T>.executeAsync(): Single<T> = Single.defer {
    try {
        Single.just(execute())
    } catch (e: Exception) {
        Single.error<T>(e)
    }
}