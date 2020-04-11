package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.*

open class IODataSource(private val ioScheduler: Scheduler) {

    fun <T> Flowable<T>.onIoScheduler(): Flowable<T> = subscribeOn(ioScheduler)

    fun <T> Observable<T>.onIoScheduler(): Observable<T> = subscribeOn(ioScheduler)

    fun <T> Single<T>.onIoScheduler(): Single<T> = subscribeOn(ioScheduler)

    fun <T> Maybe<T>.onIoScheduler(): Maybe<T> = subscribeOn(ioScheduler)

    fun Completable.onIoScheduler(): Completable = subscribeOn(ioScheduler)

}
