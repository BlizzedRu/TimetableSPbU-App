package ru.blizzed.timetablespbu.utils

import androidx.lifecycle.LiveData
import io.reactivex.*
import io.reactivex.disposables.Disposable

interface RxBinder {

    fun <T> Observable<out T>.bindTo(liveData: LiveData<Event<T>>): Disposable

    fun <T> Flowable<out T>.bindTo(liveData: LiveData<Event<T>>): Disposable

    fun <T> Single<out T>.bindTo(liveData: LiveData<Event<T>>): Disposable

    fun <T> Maybe<out T>.bindTo(liveData: LiveData<Event<T>>): Disposable

    fun <T> Completable.bindTo(liveData: LiveData<Event<T>>): Disposable

    fun clearSubscriptions()

}