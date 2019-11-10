package ru.blizzed.timetablespbu.core.mvi

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

    private val viewEvents: PublishSubject<Event> = PublishSubject.create()

    private val viewState: MutableLiveData<State> = MutableLiveData()

    fun onInitialized() {
        disposables += subscribeOnViewEvents(viewEvents)
                .scan(initialState, stateReducer)
                .subscribe(viewState::postValue, Timber::e)
    }

    fun dispatchEvent(event: Event) = viewEvents.onNext(event)

    fun observeState(lifecycleOwner: LifecycleOwner, stateObserver: (State) -> Unit) {
        viewState.observe(lifecycleOwner, Observer(stateObserver))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    protected open fun subscribeOnViewEvents(events: Observable<Event>): Observable<Change> = Observable.empty()

}
