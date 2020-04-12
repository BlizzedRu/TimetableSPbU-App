package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class SimpleCacheDataSource<Value : Any> {

  private val cache: BehaviorSubject<Value> = BehaviorSubject.create()

  fun hasValue(): Boolean = cache.hasValue()

  fun put(value: Value) = cache.onNext(value)

  fun get() = checkNotNull(cache.value)

  fun getOrNull() = cache.value

  fun observe(): Observable<Value> = cache

}
