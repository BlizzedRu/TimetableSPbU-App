package ru.blizzed.timetablespbu.mvi

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class MviViewModel<State, Event, Change> : ViewModel() {

    protected abstract val initialState: State

    protected open val stateReducer: Reducer<State, Change> = { state, _ -> state }

    protected val disposables = CompositeDisposable()

    private val events: PublishSubject<Event> = PublishSubject.create()

    private val state: MutableLiveData<State> = MutableLiveData()

    fun onInitialized() {
        disposables += bindEvents(events)
                .scan(initialState, stateReducer)
                .subscribe(state::postValue, Timber::e)
    }

    fun dispatchEvent(event: Event) = events.onNext(event)

    fun observeState(lifecycleOwner: LifecycleOwner, stateObserver: (State) -> Unit) {
        state.observe(lifecycleOwner, Observer(stateObserver))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    protected open fun bindEvents(events: Observable<Event>): Observable<Change> = Observable.empty()

}
