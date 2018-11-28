package ru.blizzed.timetablespbu.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.blizzed.timetablespbu.utils.Event.*

class BaseRxBinder(
    private val observeScheduler: Scheduler,
    private val subscribeScheduler: Scheduler
) : RxBinder {

    private val subscriptions = CompositeDisposable()

    override fun clearSubscriptions() = subscriptions.clear()

    override fun <T> Observable<out T>.bindTo(liveData: LiveData<Event<T>>): Disposable {
        return observeOn(observeScheduler)
            .subscribeOn(subscribeScheduler)
            .doOnSubscribe { postEvent(liveData, Loading()) }
            .subscribe({
                postEvent(liveData, Success(it))
            }, {
                postEvent(liveData, Error(it))
            }, {
                postEvent(liveData, Completed())
            })
            .also { subscriptions.add(it) }
    }

    override fun <T> Flowable<out T>.bindTo(liveData: MutableLiveData<Event<T>>): Disposable {
        return observeOn(observeScheduler)
            .subscribeOn(subscribeScheduler)
            .doOnSubscribe { postEvent(liveData, Loading()) }
            .subscribe({
                postEvent(liveData, Success(it))
            }, {
                postEvent(liveData, Error(it))
            }, {
                postEvent(liveData, Completed())
            })
            .also { subscriptions.add(it) }
    }

    override fun <T> Single<out T>.bindTo(liveData: MutableLiveData<Event<T>>): Disposable {
        return observeOn(observeScheduler)
            .subscribeOn(subscribeScheduler)
            .doOnSubscribe { postEvent(liveData, Loading()) }
            .subscribe({
                postEvent(liveData, Success(it))
            }, {
                postEvent(liveData, Error(it))
            })
            .also { subscriptions.add(it) }
    }

    override fun <T> Maybe<out T>.bindTo(liveData: MutableLiveData<Event<T>>): Disposable {
        return observeOn(observeScheduler)
            .subscribeOn(subscribeScheduler)
            .doOnSubscribe { postEvent(liveData, Loading()) }
            .subscribe({
                postEvent(liveData, Success(it))
            }, {
                postEvent(liveData, Error(it))
            }, {
                postEvent(liveData, Completed())
            })
            .also { subscriptions.add(it) }
    }

    override fun <T> Completable.bindTo(liveData: MutableLiveData<Event<T>>): Disposable {
        return observeOn(observeScheduler)
            .subscribeOn(subscribeScheduler)
            .doOnSubscribe { postEvent(liveData, Loading()) }
            .subscribe({
                postEvent(liveData, Completed())
            }, {
                postEvent(liveData, Error(it))
            })
            .also { subscriptions.add(it) }
    }

    private fun <T> postEvent(liveData: LiveData<Event<T>>, event: Event<T>) {
        if (liveData !is MutableLiveData) throw IllegalArgumentException("Mutable live data expected.")
        liveData.postValue(event)
    }
}