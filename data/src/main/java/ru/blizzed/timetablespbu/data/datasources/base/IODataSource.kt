package ru.blizzed.timetablespbu.data.datasources.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

open class IODataSource(private val ioScheduler: Scheduler) {

    fun <T> Flowable<T>.onIoScheduler(): Flowable<T> = subscribeOn(ioScheduler)

    fun <T> Observable<T>.onIoScheduler(): Observable<T> = subscribeOn(ioScheduler)

    fun <T> Single<T>.onIoScheduler(): Single<T> = subscribeOn(ioScheduler)

    fun <T> Maybe<T>.onIoScheduler(): Maybe<T> = subscribeOn(ioScheduler)

    fun Completable.onIoScheduler(): Completable = subscribeOn(ioScheduler)

}
