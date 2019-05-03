package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

open class DatabaseDataSource(private val dbScheduler: Scheduler) {

    fun <T> Flowable<T>.onDbScheduler(): Flowable<T> = subscribeOn(dbScheduler)

    fun <T> Observable<T>.onDbScheduler(): Observable<T> = subscribeOn(dbScheduler)

    fun <T> Single<T>.onDbScheduler(): Single<T> = subscribeOn(dbScheduler)

    fun <T> Maybe<T>.onDbScheduler(): Maybe<T> = subscribeOn(dbScheduler)

    fun Completable.onDbScheduler(): Completable = subscribeOn(dbScheduler)

}
